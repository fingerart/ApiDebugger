/*
 * Copyright 2010-present ApiDebugger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.chengguo.api.debugger.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class ApiVisitor<R> extends PsiElementVisitor {

  public R visitProperty(@NotNull ApiProperty o) {
    return visitElement(o);
  }

  public R visitElement(@NotNull ApiElement o) {
    super.visitElement(o);
    return null;
  }

}
