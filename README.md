
# ericsson-demo
Repo for Ericsson API Lifecycle demo.

## Introduction

This demo stages an application and the respective exposed API on 3scale though three stages via a Jenkins pipeline on OpenShift Container platform.

1. Dev: Group IT
2. Test: SAN Tools
3. Prod Managed Services

API's will be testing on the application and on the API Management level via Karate from Intuit (https://github.com/intuit/karate).

The application is based on Quarkus (https://quarkus.io) and exposing a simple "Pet" REST based service.

## Create pipeline

First login and create a **petstore** project

	oc login -u **admin-user** **openshift-master-domain**
	oc new-project petstore
	
The create a credentials-file for the 3scale toolbox create a file called **.3scalerc.yml**:

	cat << EOF > .3scalerc.yaml
	---
	:remotes:
	  3scale-tenant-groupit:
	    :authentication: <provider-key>
	    :endpoint: <admin-domain>
	  3scale-tenant-santools:
	    :authentication: <provider-key>
	    :endpoint: <admin-domain>
	  3scale-tenant-managed-services:
	    :authentication: <provider-key>
	    :endpoint: <admin-domain>
	EOF

The you can create a secret file with the credentials

	oc create secret generic 3scale-toolbox --from-file=.3scalerc.yaml

Now we are all set to create the actual pipeline and the three stages:

	oc new-app -f https://raw.githubusercontent.com/mdiwing/ericsson-demo/master/pipeline/pipeline-template.yaml

	oc new-project petstore-dev
	oc new-project petstore-test
	oc new-project petstore-prod

	oc project petstore

	oc policy add-role-to-user admin system:serviceaccount:petstore:jenkins -n petstore-dev
	oc policy add-role-to-user admin system:serviceaccount:petstore:jenkins -n petstore-test
	oc policy add-role-to-user admin system:serviceaccount:petstore:jenkins -n petstore-prod

## Via Web-Console

On the OpenShift Web-Console navigate to:

Projects → Petstore → Builds → Pipelines → Start Pipeline

## Via CLI

The pipeline can be started using the **oc** CLI:

	oc project petstore
	oc start-build threescale-api-publish-pipeline

## Cleanup

To clean up the setup run the following commands via the **oc** CLI:

	oc delete project petstore
	oc delete project petstore-dev
	oc delete project petstore-test
	oc delete project petstore-prod

