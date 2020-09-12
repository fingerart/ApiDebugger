package io.chengguo.api.debugger.lang.environment;

import com.intellij.ide.scratch.ScratchUtil;
import com.intellij.json.psi.JsonFile;
import com.intellij.json.psi.JsonObject;
import com.intellij.json.psi.JsonProperty;
import com.intellij.json.psi.JsonValue;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.*;
import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.EnumeratorStringDescriptor;
import com.intellij.util.io.KeyDescriptor;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ApiEnvironmentIndex extends FileBasedIndexExtension<String, Set<String>> {
    public static final ID<String, Set<String>> KEY = ID.create("api.debugger.environment");
    private static final ApiEnvironmentInputFilter API_ENVIRONMENT_INPUT_FILTER = new ApiEnvironmentInputFilter();

    @NotNull
    @Override
    public ID<String, Set<String>> getName() {
        return KEY;
    }

    @NotNull
    @Override
    public DataIndexer<String, Set<String>, FileContent> getIndexer() {
        return new DataIndexer<String, Set<String>, FileContent>() {
            @NotNull
            @Override
            public Map<String, Set<String>> map(@NotNull FileContent inputData) {
                PsiFile file = inputData.getPsiFile();
                assert file instanceof JsonFile;
                JsonValue root = ((JsonFile) file).getTopLevelValue();
                if (!(root instanceof JsonObject)) {
                    return Collections.emptyMap();
                }
                Map<String, Set<String>> result = new THashMap<>();
                for (JsonProperty property : ((JsonObject) root).getPropertyList()) {
                    JsonValue value = property.getValue();
                    if (value instanceof JsonObject) {
                        result.put(property.getName(), readEnvVariables((JsonObject) value));
                    }
                }
                return result;
            }
        };
    }

    @NotNull
    @Override
    public KeyDescriptor<String> getKeyDescriptor() {
        return EnumeratorStringDescriptor.INSTANCE;
    }

    @NotNull
    @Override
    public DataExternalizer<Set<String>> getValueExternalizer() {
        return new DataExternalizer<Set<String>>() {
            @Override
            public void save(@NotNull DataOutput out, Set<String> value) throws IOException {
                out.writeInt(value.size());
                for (String s : value) {
                    EnumeratorStringDescriptor.INSTANCE.save(out, s);
                }
            }

            @Override
            public Set<String> read(@NotNull DataInput in) throws IOException {
                final THashSet<String> set = new THashSet<>();
                for (int r = in.readInt(); r > 0; --r) {
                    set.add(EnumeratorStringDescriptor.INSTANCE.read(in));
                }
                return set;
            }
        };
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @NotNull
    @Override
    public FileBasedIndex.InputFilter getInputFilter() {
        return API_ENVIRONMENT_INPUT_FILTER;
    }

    @Override
    public boolean dependsOnFileContent() {
        return true;
    }

    @NotNull
    private static Set<String> readEnvVariables(@NotNull final JsonObject obj) {
        final List<JsonProperty> properties = obj.getPropertyList();
        if (properties.isEmpty()) {
            return Collections.emptySet();
        }
        return properties.stream().map(property -> StringUtil.nullize(property.getName())).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @NotNull
    public static Collection<String> getAllEnvironments(@NotNull Project project) {
        GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
        return getAllEnvironments(project, scope);
    }

    @NotNull
    public static Collection<String> getAllEnvironments(@NotNull Project project, @NotNull GlobalSearchScope scope) {
        FileBasedIndex index = FileBasedIndex.getInstance();
        return index.getAllKeys(KEY, project).stream().filter(env -> !index.getContainingFiles(KEY, env, scope).isEmpty()).collect(Collectors.toSet());
    }

    @NotNull
    public static Collection<String> getAllEnvironments(@NotNull Project project, @Nullable PsiFile file) {
        return getAllEnvironments(project, getSearchScope(project, file));
    }

    @NotNull
    public static Collection<String> getAllVariables(@NotNull Project project, @Nullable PsiFile contextFile) {
        GlobalSearchScope scope = getSearchScope(project, contextFile);
        Collection<String> environments = getAllEnvironments(project, scope);
        if (environments.isEmpty()) {
            return Collections.emptySet();
        }
        Set<String> variables = new HashSet<>();
        for (String env : environments) {
            final List<Set<String>> varsInEnv = FileBasedIndex.getInstance().getValues(KEY, env, scope);
            for (Set<String> vars : varsInEnv) {
                variables.addAll(vars);
            }
        }
        return variables;
    }

    public static Collection<String> getAllVariables(Project project, String env, PsiFile contextFile) {
        List<Set<String>> variables = FileBasedIndex.getInstance().getValues(KEY, env, getSearchScope(project, contextFile));
        return variables.size() == 1 ? variables.get(0) : Collections.emptyList();
    }

    public static Collection<VirtualFile> getAllVirtualFiles(Project project, String evn) {
        GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
        return FileBasedIndex.getInstance().getContainingFiles(KEY, evn, scope);
    }

    @NotNull
    public static GlobalSearchScope getSearchScope(@NotNull final Project project, @Nullable final PsiFile contextFile) {
        GlobalSearchScope projectScope = GlobalSearchScope.projectScope(project);
        VirtualFile context = (contextFile != null) ? contextFile.getVirtualFile() : null;
        if (contextFile != null && !ScratchUtil.isScratch(context) && !projectScope.contains(context)) {
            VirtualFile dir = context.getParent();
            if (dir != null) {
                VirtualFile[] files = dir.getChildren();
                return GlobalSearchScope.filesScope(project, (files.length > 0) ? ContainerUtil.newArrayList(files) : Collections.emptyList());
            }
        }
        return projectScope;
    }
}
