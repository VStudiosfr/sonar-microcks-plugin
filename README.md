# [DRAFT] Sonarqube Plugin for Microcks

# What does it provide ?

If you have a setup in your organisation, a central Microcks instance that gather all the mocks of your different
artefacts, this Sonarqube plugin will allow you to control:

- The average API conformance index of the artifact
- The lowest API conformance index of the artifact
- The number of mocks for each artefact

Centralizing thoses informations in Sonarqube will allow you to setup Quality Gates

# Is it a good solution ?

https://discordapp.com/channels/1162356525969449100/1316376708454154351/1317159744645107794

# How to install

Each of your Service deployed on Microcks must have a label that should correspond to the artefact name in Sonarqube:
`artefact=<sonarqubeArtefactName>`

1. Install your plugin on SonarQube
2. Configure for each artefact, the credentials to have access to Microcks:

- Microcks URL
- Microcks Identity Provider (compatible OAuth 2 Client Credentials):
    - URL
    - Callback URL
    - scope
    - ClientId
    - ClientSecret

3. Enjoy

# How to develop

## Local testing environment

`cd localdevenvironment`
`docker compose up -d`

