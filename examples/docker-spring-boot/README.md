# core-services-template

Sets up CI / CD:  Git -> Rio -> ACI Kube integration

## Prerequisites:
1.	Install Kube Cli by following the instructions on below page
	`https://kube.apple.com/docs/guides/getting-started`

2.	Cluster Access : Self add yourself to `apc-usuqo1` & `aci-kube-prod-users` AppleDirectory groups.
	
3.	Namespace Access (applepay) : Get yourself added to `kube-access-applepay@group.apple.com` Apple Directory group


## Core Service onboarding steps :
1.	Create new GIT Repository using `core-services-template` as template repo, add application code
		
2.	Execute `sh setup-scripts/onboarding.sh` from project HOME to configure rio.yml, deployment manifest files, and to create the rio.apple.com project with required secrets.

3.	Go to rio.apple.com, search for the service in the projects, and click GitHub installation to authorize rio to connect to git repo to monitor commits
	
4.	Commit the changes, automatic build & deploy should trigger.
	This will deploy the application and will be accessible at https://nnines.apple.com/servicecontext



## Providing custom build instructions

We currently use the `buildozer:v4:build` template of rio, that automatic detect the type of project and invokes the corresponding builder to run.

You can override the automatic detection by manually specifying the list of builders to run in the builds:builders: array in the rio.yaml.
			
	Example below for specifying ruby-bundle builder :
	
	```
	schemaVersion: 2.0

	pipelines:
	- branchName: master
	build:
		template: buildozer:v4:build
		builders:
		- ruby-bundle
	```
			
	Reference from rio documentation : https://docs.aci.apple.com/rio/guide-to-rio/schema.html#guide-to-rio-schema-build-builders-2-0



## Dockerfile
This template provides a sample Dockerfile, that will work for any application which will produce a .war artifact and will run on a tomcat.

For different application needs, please update the Dockerfile with your project specific commands



## Managing application Secrets

If the application needs secrets, fill Secrets template, trigger create-secret script
	
	Execute the `./setup-scripts/create-secret.sh` Script, to add any new secret in kubernetes to be used by your application
		
	`create-secret.sh` script requires 4 inputs :
	1.	Namespace of the cluster which needs to be used.
	2.	Kubernetes secret object name which will be created inside the cluster by kubernetes and it will
		referred in your POD deployment .yaml file to mount as volume or ENV variable.
	3.	Secret Data name which will be the name of the file and will be referred in the application to read the file and load the properties.
		Please avoid putting the "." character in the Secret Data Name.
	4.	The name of the property file which needs to be placed in the same directory where the 
		create-secret.sh is available. The script will read the file and encrypt the content of the file 
		and create the Kubernetes secret object with the name provided in the step 2.	
