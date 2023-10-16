fun properties(key: String) = project.findProperty(key).toString()

plugins {
    `version-catalog`
    `maven-publish`
    signing
}

group = properties("GROUP")
version = properties("VERSION")

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
            groupId = properties("GROUP")
            artifactId = properties("POM_ARTIFACT_ID")
            version = properties("VERSION")

            pom {
                name.set(properties("POM_NAME"))
                description.set(properties("POM_DESCRIPTION"))
                url.set(properties("POM_URL"))
                inceptionYear.set(properties("POM_INCEPTION_YEAR"))

                licenses {
                    license {
                        name.set(properties("POM_LICENSE_NAME"))
                        url.set(properties("POM_LICENSE_URL"))
                        distribution.set(properties("POM_LICENSE_DIST"))
                    }
                }

                developers {
                    developer {
                        id = "raghudg"
                        name = "Raghavendra"
                        email = "raaghavendradg@gmail.com"
                    }
                }

                scm {
                    url.set(properties("POM_SCM_URL"))
                    connection.set(properties("POM_SCM_CONNECTION"))
                    developerConnection.set(properties("POM_SCM_DEV_CONNECTION"))
                }

                issueManagement {
                    system.set(properties("POM_ISSUES_SYSTEM"))
                    url.set(properties("POM_ISSUES"))
                }
            }
        }

        repositories {
            maven {
                maven {
                    name = "ossrh"
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = properties("username")
                        password = properties("password")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}