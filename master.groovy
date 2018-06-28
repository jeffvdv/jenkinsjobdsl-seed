import org.yaml.snakeyaml.Yaml

folder("seedjobs")

new Yaml().load(readFileFromWorkspace("cfg/config.yaml")).each { p ->
    def projecttitle = p.projecttitle
    def jobbranch = 'master'
    def jobrepo = p.jobrepo

    job("seedjobs/${projecttitle}-seedjob") {

        scm {
            git("${jobrepo}", "${jobbranch}")
        }

        steps {
            dsl {
                external('*.groovy')
                removeAction('DELETE')
                removeViewAction('DELETE')
                additionalClasspath('lib/*')
            }
        }
    }
}
