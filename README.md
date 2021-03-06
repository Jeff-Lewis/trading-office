# Trading Office [![Build Status](https://travis-ci.org/spolnik/trading-office.svg?branch=master)](https://travis-ci.org/spolnik/trading-office)

Trading Office is reference implementation of microservices architecture, based on Spring Boot, Heroku, RabbitMQ, MongoDB and Spring Cloud Netflix components. 

It's modeling part of post trade processing, mainly focused on receiving Fixml message and preparing confirmation for it.

- [Introduction](#introduction)
- [Components](#components)
- [E2E Test](#e2e-test)
- [Continuous Delivery](#continuous-delivery)
- [Infrastructure](#infrastructure)
- [Notes](#notes)

## Introduction

- set of applications simulating simple flow in post trade part of trade lifecycle.
- it's focused on generating confirmation based on received allocation report (FIXML)
- [Running locally](https://github.com/spolnik/trading-office/wiki)

![Component Diagram](https://raw.githubusercontent.com/spolnik/trading-office/master/design/component_diagram.png)

## Components
- [Allocation Message Receiver](https://github.com/spolnik/trading-office-allocation-message-receiver)
- [Allocation Enricher](https://github.com/spolnik/trading-office-allocation-enricher)
- [Confirmation Sender](https://github.com/spolnik/trading-office-confirmation-sender)
- [Market Data Service](https://github.com/spolnik/trading-office-market-data-service)
- [Confirmation Service](https://github.com/spolnik/trading-office-confirmation-service)
- [Counterparty Service](https://github.com/spolnik/trading-office-counterparty-service)
- [Eureka Server](https://github.com/spolnik/trading-office-eureka-server)
- [API Gateway](https://github.com/spolnik/trading-office-api-gateway)

## E2E Test
- end to end tests written in spock
- it runs against deployed applications (Heroku)
- it covers 4 cases, 2 for PROD and 2 for STAGING. Those 2 cases are: SWIFT and EMAIL confirmations

## Continuous Delivery

- initially, developer push his changes to GitHub
- in next stage, GitHub notifies Travis CI about changes
- Travis CI runs whole continuous integration flow, running compilation, tests and generating reports
- static code analysis report is sent to SonarQube
- coverage report is sent to Codecov
- application is deployed into Heroku Staging machine
- administrator once he is happy with quality of staging application, he promotes it to production

![Continuous Delivery Diagram](https://raw.githubusercontent.com/spolnik/trading-office/master/design/continuous_delivery.png)

=========

## Infrastructure
- Heroku (PaaS)
- Heroku Add-ons (monitoring - new relic)
- RabbitMQ (CloudAMQP hosted on heroku)
- SonarQube (hosted on OpenShift) - https://sonar-nprogramming.rhcloud.com
- TravisCI - https://travis-ci.org/spolnik/trading-office

## Domain

- Swift - http://www.iso15022.org/uhb/uhb/finmt518.htm
- FIXML - http://btobits.com/fixopaedia/fixdic50-sp2-ep/index.html (Allocation Report message)
- Trade Lifecycle - http://thisweekfinance.blogspot.com/2011/10/trade-life-cycle.html

![Trade Lifecycle](https://raw.githubusercontent.com/spolnik/trading-office/master/design/trade_lifecycle.jpg)

## Notes
- to run integration tests, you have to have both tools up and running - rabbitmq & mongodb
- [Running locally](https://github.com/spolnik/trading-office/wiki)
- [Travis Builds](https://travis-ci.org/spolnik)
- central log management - [managed ELK on sematext](https://apps.sematext.com/users-web/index.do)
