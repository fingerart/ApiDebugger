import {highlightBlock} from "highlight.js";

class Main {

    constructor() {
        this.setupEvent()
        this.print()
    }

    private setupEvent() {
        document.addEventListener("DOMContentLoaded", evt => {
            document.querySelectorAll('pre code').forEach((block) => {
                highlightBlock(null)
                console.log("end")
            });
        })
    }

    private print() {
        console.log("hello world.")
    }
}

new Main();