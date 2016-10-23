package test.rollback

import grails.transaction.Transactional

@Transactional
class FilmService {

    def create() {
        def film = new Film(name: "Anthropoid")
        film.save(failOnError: true)

        return film
    }
}
