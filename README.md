# webcrawler

A Simple crawler which crawls a specified website and generate a simple site map in json format.
It has a REST endpoint and a CLI to initiate the crawling process. 

## Design 
This appliction is implemented using Spring boot. Key design element is the seperation of concern between the crawling function and site map generation. A crawler implementation class ( com.casestudy.webcrawler.core.Crawler) parse a website to a configurable depth and generate events for a plugged Scrapper to generate Site map using a model class com.casestudy.webcrawler.core.model.Site.
this application uses JSoup library for parsing html DOM. 

## Usage 
use mvn clean install to build the project , It will create webcrawler-0.0.1-SNAPSHOT.jar package.
run the application jar using java -jar webcrawler-0.0.1-SNAPSHOT.jar to launch the web application at port 8080.
use the REST endpoint - http://localhost:8080/crawl?url=https://www.onepagelove.com/ to crawl a website identified with url parameter.

## Extensibility 
A more complex and capable Scrapper can be implemented and configured to be used with the same crawler.
The application can be enhanced to support Robot.txt and meta tags.
code quality/readability,code-coverage can be enhanced with more inline comments and elaborated logging using logging framework.
Also, as future extension,an async model of trigerring web crawling would be a better choice since crawling can be time consuming process. An async API would acknowledge a crawling request and trigger crawling in background as a batch job and will return a pointer ( e.g. a REST resource ID ) which the caller can later access to download the generated sitemap.





