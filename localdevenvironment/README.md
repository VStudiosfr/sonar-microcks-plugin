# Local Environment of Development

Mettez votre plugin à tester dans `/sonarqube_ext_plugins`
`docker-compose up -d`

## Url des outils

* Microcks: http://localhost:38080 (admin:microcks123)
* Keycloak as Identity Provider: http://localhost:18080 (admin:admin) -> http://localhost:18080/realms/microcks
* Sonarqube: http://localhost:19000 (admin:admin) - Debug on http://localhost:18000