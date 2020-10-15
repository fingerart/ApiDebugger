import {Login} from "./login";
import hl from 'highlight.js';

class Main {

    constructor() {
        this.setupEvent()
        this.print()
    }

    private setupEvent() {
        hl.registerLanguage("json", hljs => hljs.requireLanguage("json"))
        document.addEventListener("DOMContentLoaded", evt => {
            document.querySelectorAll('pre code').forEach((block) => {
                console.log("end", "over", ".")
                new Login().doLogin()
            });
        })
    }

    private print() {
        console.log("hello world.")
    }
}

new Main();