module.exports = function (grunt) {
    "use strict";

    grunt.initConfig({
        ts: {
            options: {
                comments: false,               // same as !removeComments. [true | false (default)]
                target: 'es5',                 // target javascript language. [es3 | es5 (grunt-ts default) | es6]
                module: 'amd',                 // target javascript module style. [amd (default) | commonjs]
                sourceMap: true,               // generate a source map for every output js file. [true (default) | false]
                declaration: false,            // generate a declaration .d.ts file for every output js file. [true | false (default)]
            },
            build: {
                src: ["./app.ts"],             // The source typescript files, http://gruntjs.com/configuring-tasks#files
                out: '../gen/resources/htmlTemplates/app.js',   // If specified, generate an out.js file which is the merged js file
            }
        },
        uglify: {
            min: {
                files: {'../gen/resources/htmlTemplates/app.min.js': ['../gen/resources/htmlTemplates/app.js']}
            }
        },
        copy: {
            builds: {expand: true, cwd: 'build/', src: '*', dest: 'test/'}
        }
    });

    grunt.loadNpmTasks("grunt-ts");
    grunt.loadNpmTasks("grunt-contrib-copy");
    grunt.loadNpmTasks("grunt-contrib-uglify");

    grunt.registerTask("default", ["ts:build", "uglify:min"/*, "copy:builds"*/]);
};