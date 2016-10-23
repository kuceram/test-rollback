package test.rollback

class FilmController {

    static responseFormats = ["json"]

    def filmService

    def list() {
        respond Film.list()
    }

    def create() {
        def film = filmService.create()

        respond film
    }
}
