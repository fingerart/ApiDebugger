interface Registry {

}

class App implements Registry {
    print() {
        console.log("hello")
    }
}

new App();