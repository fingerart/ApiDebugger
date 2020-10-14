module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        // 编译 LESS
        less: {
            development: {
                options: {
                    relativeUrls: true,
                    paths: ['assets/theme'],
                    plugins: [
                        new (require('less-plugin-autoprefix'))({browsers: ["last 2 versions"]}),
                    ],
                },
                files: {
                    '../gen/resources/htmlTemplates/style.min.css': 'assets/theme/all.less'
                }
            },
            production: {
                options: {
                    sourceMap: true,
                    compress: true,
                    yuicompress: true,
                    optimization: 1,
                    plugins: [
                        new (require('less-plugin-autoprefix'))({browsers: ["last 2 versions"]}),
                        // new (require('less-plugin-clean-css'))({advanced: true, compatibility: 'ie8'}),
                    ],
                    modifyVars: {
                        imgPath: '"http://cdn.tenon.dev"'
                    }
                },
                files: {
                    '../gen/resources/htmlTemplates/style.min.css': 'assets/theme/all.less'
                }
            },
        },
        // 编译 TS
        ts: {
            options: {
                allowSyntheticDefaultImports: true,
                comments: false,               // same as !removeComments. [true | false (default)]
                target: 'es5',                 // target javascript language. [es3 | es5 (grunt-ts default) | es6]
                module: 'commonjs',                 // target javascript module style. [amd (default) | commonjs]
                sourceMap: false,               // generate a source map for every output js file. [true (default) | false]
                declaration: false,            // generate a declaration .d.ts file for every output js file. [true | false (default)]
                locale: "zh-cn",
            },
            build: {
                src: ["src/**/*.ts"],             // The source typescript files, http://gruntjs.com/configuring-tasks#files
                out: "../gen/resources/htmlTemplates/main.min.js",       // If specified, generate an out.js file which is the merged js file
            }
        },
        // 压缩 JS
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */',
                output: {
                    comments: false,
                    ascii_only: true
                },
            },
            min: {
                files: [{
                    src: ["../gen/resources/htmlTemplates/main.min.js"],
                    dest: "../gen/resources/htmlTemplates/main.min.js",
                }]
            }
        },
        // 压缩 HTML
        htmlmin: {
            dev: {
                files: {
                    '../gen/resources/htmlTemplates/index.html': 'src/index.html'
                }
            },
            dist: {
                options: {
                    removeComments: true,
                    collapseWhitespace: true
                },
                files: {
                    '../gen/resources/htmlTemplates/index.html': 'src/index.html'
                }
            }
        },
        // 复制文件
        copy: {
            assets: {
                files: [{
                    expand: true,
                    cwd: '.',
                    src: ['assets/images/*'],
                    dest: '../gen/resources/htmlTemplates/'
                }, {
                    expand: false,
                    src: ['assets/sample/data.json'],
                    dest: '../gen/resources/htmlTemplates/assets/raw/db.json'
                }]
            }
        },
        // 监听文件变化
        watch: {
            options: {
                livereload: true,
            },
            ts: {
                files: ['src/**/*.ts'],
                tasks: ['ts:build'],
                options: {
                    spawn: false,
                },
            },
            less: {
                files: ['assets/theme/**/*.less'],
                tasks: ['less:development'],
                options: {
                    spawn: false,
                },
            },
            html: {
                files: ['src/index.html'],
                tasks: ["htmlmin:dev"],
                options: {
                    spawn: false,
                },
            },
            assets: {
                files: ['assets/images/*', 'assets/sample/data.json'],
                tasks: ["copy:assets"],
                options: {
                    spawn: false,
                },
            }
        },
        // 清除文件
        clean: {
            options: {
                force: true
            },
            build: ['../gen/resources/htmlTemplates/*']
        },
        // 本地服务
        connect: {
            server: {
                options: {
                    hostname: 'localhost',
                    useAvailablePort: true,
                    livereload: true,
                    open: true,
                    base: '../gen/resources/htmlTemplates'
                }
            },
            'pdf.js': {
                options: {
                    hostname: 'localhost',
                    useAvailablePort: true,
                    livereload: false,
                    open: true,
                    base: '../../pdf.js'
                }
            }
        },
    });

    grunt.loadNpmTasks("grunt-ts");
    grunt.loadNpmTasks("grunt-contrib-uglify");
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-connect');

    grunt.registerTask("development", ["clean:build", "copy:assets", "htmlmin:dev", "less:development", "ts:build"]);
    grunt.registerTask("release", ["clean:build", "copy:assets", "htmlmin:dist", "less:production", "ts:build", "uglify:min"]);
    grunt.registerTask("start", ["clean:build", "development", "connect:server", "connect:pdf.js", "watch"]);
};