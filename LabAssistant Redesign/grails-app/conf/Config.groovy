// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
		grails.gorm.failOnError=true
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
		grails.gorm.failOnError=true
    }

}

// log4j configuration
import org.apache.log4j.Level
log4j = {
	
	
	appenders {
		console name:'console', threshold:Level.ERROR,layout:pattern(conversionPattern: '%p %d{ISO8601} %c{4} %m%n')
		rollingFile name:"rollingFileTrace", threshold:org.apache.log4j.Level.TRACE, maxFileSize:1048576,  file:'Trace.log', layout:pattern(conversionPattern: '%p %d{ISO8601} %c{5} %m%n')
		rollingFile name:"rollingFileDebug", threshold:org.apache.log4j.Level.DEBUG, maxFileSize:1048576,file:'Debug.log', layout:pattern(conversionPattern: '%p %d{ISO8601} %c{5} %m%n')
		rollingFile name:"rollingFileError", threshold:org.apache.log4j.Level.ERROR, maxFileSize:1048576,file:'Error.log', layout:pattern(conversionPattern: '%p %d{ISO8601} %c{5} %m%n')
	}

	  error console, rollingFileDebug, rollingFileError, rollingFileTrace: 'org.codehaus.groovy.grails.web.servlet',  //  controllers
		'org.codehaus.groovy.grails.web.pages', //  GSP
		'org.codehaus.groovy.grails.web.sitemesh', //  layouts
		'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
		'org.codehaus.groovy.grails.web.mapping', // URL mapping
		'org.codehaus.groovy.grails.commons', // core / classloading
		'org.codehaus.groovy.grails.plugins', // plugins
		'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
		'org.springframework',
		'org.hibernate',
        'net.sf.ehcache.hibernate'
	
	  debug rollingFileDebug, rollingFileTrace: 'org.hibernate',
		'com.britetab',
		'BootStrap',
		'org.apache.ddlutils'
	
	  trace rollingFileTrace: 'org.hibernate.SQL',
		'org.hibernate.type'
	
	  warn  console,rollingFileDebug,rollingFileTrace: 'org.mortbay.log',
		'org.hibernate.tool.hbm2ddl'
	
	 root {
	  error 'console','rollingFileError','rollingFileDebug','rollingFileTrace'
	  additivity = true
	 }
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'edu.gatech.youngguns.labassistant.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'edu.gatech.youngguns.labassistant.UserRole'
grails.plugins.springsecurity.authority.className = 'edu.gatech.youngguns.labassistant.Role'

// Default landing page after login
login.success.defaultUrl = '/home/index'

import grails.plugins.springsecurity.SecurityConfigType

grails.plugins.springsecurity.rejectIfNoRule = false
grails.plugins.springsecurity.securityConfigType = SecurityConfigType.Annotation
