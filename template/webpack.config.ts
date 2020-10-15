import * as webpack from "webpack";
import * as path from "path";
import {CleanWebpackPlugin} from "clean-webpack-plugin";

const ExtractTextPlugin = require("extract-text-webpack-plugin");

const HtmlWebpackPlugin = require("html-webpack-plugin")

const config: webpack.Configuration = {
    mode: "development",
    entry: {
        app: "./src/main.ts"
    },
    output: {
        path: path.resolve(__dirname, "../gen/resources/htmlTemplates"),
        filename: "[name].bundle.js"
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: "ts-loader",
                exclude: /node_modules/
            },
            {
                test: /\.(less|css)$/,
                loader: ExtractTextPlugin.extract('style', 'css!less')
            }
        ]
    },
    plugins: [
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            template: "./src/index.html"
        }),
        new ExtractTextPlugin({filename: "style.css"})
    ]
}

export default config;