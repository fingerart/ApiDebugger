module.exports = function (grunt) {

    grunt.initConfig({
        less: {
            development: {
                options: {
                    paths: ['assets/theme'],
                    plugins: [
                        new (require('less-plugin-autoprefix'))({browsers: ["last 2 versions"]}),
                    ],
                },
                files: {
                    '../gen/resources/htmlTemplates/style.css': 'assets/theme/all.less'
                }
            },
            production: {
                options: {
                    paths: ['assets/theme'],
                    sourceMap: true,
                    plugins: [
                        new (require('less-plugin-autoprefix'))({browsers: ["last 2 versions"]}),
                        new (require('less-plugin-clean-css'))({advanced: true})
                    ],
                    modifyVars: {
                        imgPath: '"http://mycdn.com/path/to/images"',
                        bgColor: 'red'
                    }
                },
                files: {
                    '../gen/resources/htmlTemplates/style.min.css': 'assets/theme/all.less'
                }
            }
        },
        ts: {
            options: {
                comments: false,               // same as !removeComments. [true | false (default)]
                target: 'es5',                 // target javascript language. [es3 | es5 (grunt-ts default) | es6]
                module: 'amd',                 // target javascript module style. [amd (default) | commonjs]
                sourceMap: true,               // generate a source map for every output js file. [true (default) | false]
                declaration: true,             // generate a declaration .d.ts file for every output js file. [true | false (default)]
                locale: "zh-cn",
            },
            build: {
                src: ["src/**/*.ts"],             // The source typescript files, http://gruntjs.com/configuring-tasks#files
                out: "../gen/resources/htmlTemplates/main.js",       // If specified, generate an out.js file which is the merged js file
            }
        },
        uglify: {
            min: {
                files: [{
                    src: ["../gen/resources/htmlTemplates/main.js"],
                    dest: "../gen/resources/htmlTemplates/main.min.js",
                }]
            }
        },
        copy: {
            assets: {
                files: [{
                    expand: true,
                    cwd: '.',
                    src: ['assets/images/*'],
                    dest: '../gen/resources/htmlTemplates/'
                },{
                    expand: false,
                    src: ['assets/sample/data.json'],
                    dest: '../gen/resources/htmlTemplates/assets/raw/db.json'
                }]
            },
            html: {
                files: [{
                    expand: true,
                    flatten: true,
                    src: ['src/index.html'],
                    dest: '../gen/resources/htmlTemplates/'
                }]
            }
        },
        watch: {
            ts: {
                files: ['src/**/*.ts'],
                tasks: ['ts:build'],
                options: {
                    spawn: false,
                },
            },
            html: {
                files: ['src/index.html'],
                tasks: ["copy:html"],
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
    });

    grunt.loadNpmTasks("grunt-ts");
    grunt.loadNpmTasks("grunt-contrib-uglify");
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-less');

    grunt.registerTask("build", ["ts:build", "uglify:min"]);
    grunt.registerTask("start", ["watch"]);
    grunt.registerTask("buildStyle", ["less:development"]);
    grunt.registerTask("copyAssets", ["copy:assets", "copy:html"]);
};