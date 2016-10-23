import grails.converters.JSON
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.ContentType
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Value
import spock.lang.Specification

@Integration
@Rollback
class FilmSpec extends Specification {

    @Value('${local.server.port}')
    Integer port

    def "list film ok"() {
        setup:
            def httpClient = HttpClients.createDefault()
            // path
            def uriBuilder = new URIBuilder("http://localhost:$port/")
            def url = uriBuilder.setPath("/film/list")
            // headers
            def httpGet = new HttpGet(url.build().normalize())
            httpGet.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()))
        when:
            def response = httpClient.execute(httpGet)
        then:
            response.statusLine.statusCode == 200
        and:
            def responseJSON = JSON.parse(EntityUtils.toString(response.entity))
            responseJSON.size() == 0
    }


    def "create film"() {
        setup:
            def httpClient = HttpClients.createDefault()
            // path
            def uriBuilder = new URIBuilder("http://localhost:$port/")
            def url = uriBuilder.setPath("/film/create")
            // headers
            def httpPost = new HttpPost(url.build().normalize())
            httpPost.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()))
        when:
            def response = httpClient.execute(httpPost)
        then:
            response.statusLine.statusCode == 200
    }

    def "list film error"() {
        setup:
            def httpClient = HttpClients.createDefault()
            // path
            def uriBuilder = new URIBuilder("http://localhost:$port/")
            def url = uriBuilder.setPath("/film/list")
            // headers
            def httpGet = new HttpGet(url.build().normalize())
            httpGet.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()))
        when:
            def response = httpClient.execute(httpGet)
        then:
            response.statusLine.statusCode == 200
        and:
            def responseJSON = JSON.parse(EntityUtils.toString(response.entity))
            responseJSON.size() == 0
    }
}
