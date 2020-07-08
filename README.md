# NeoLoad SuperMon Integration
<p align="center"><img src="/screeshots/PNG 4.png" width="40%" alt="SuperMon Logo" /></p>

This project is aimed to integrate NeoLoad with Supermon, giving users the ability to track database behavior during a load test.
This project has 2 disctinct components :
* `SupermonContext` : Custom action to add in NeoLoad to send the Project Context to NeoLoad Web.
* `WebhookHandler` : Service that will receive the NeoLoad Web Test started and Test ended notification ( through WebHook)
## SupermonContext
This custom action will allow you to add all the project information required in XRAY :
   * `schemeID` (Required) : Scheme id of the database monitored by Supermon
   * `databaseType` (Optional): Type of database ( mysql,oracle...Etc)
   * `databaseName` (Optional) : Name of the database
   * `useCaseIdentifier` (Required) : Name of the test

DatabaseType and databaseName will structure the data collected from Supermon in NeoLoad WEB

SupermonContext will update the test results in NeoLoad web with all the information required to be able to Interact with SUpermon during the test

     
| Property | Value |
| -----| -------------- |
| Maturity | Experimental |
| Author   | Neotys Partner Team |
| License  | [BSD Simplified](https://www.neotys.com/documents/legal/bsd-neotys.txt) |
| NeoLoad  | 7.0 (Enterprise or Professional Edition w/ Integration & Advanced Usage and NeoLoad Web option required)|
| Requirements | NeoLoad Web |
| Bundled in NeoLoad | No
| Download Binaries | <ul><li>[latest release]() is only compatible with NeoLoad from version 7.0</li><li> Use this [release](https://github.com/Neotys-Labs/Dynatrace/releases/tag/Neotys-Labs%2FDynatrace.git-2.0.10) for previous NeoLoad versions</li></ul>|

### Installation

1. Download the [latest release]() for NeoLoad from version 7.0
1. Read the NeoLoad documentation to see [How to install a custom Advanced Action](https://www.neotys.com/documents/doc/neoload/latest/en/html/#25928.htm).

<p align="center"><img src="/screeshots/customactions.png" alt="SupermonContext Advanced Action" /></p>

### NeoLoad Set-up

Once installed, how to use in a given NeoLoad project:

1. Create a `SuperMonContext` User Path.
1. Insert `SuperMonContext` in the `Init` block.
<p align="center"><img src="/screeshots/vu.png" alt="SuperMonContext User Path" /></p>
1. Add a `Delay` in the  `Action` block
<p align="center"><img src="/screeshots/vu_settings.png" alt="SuperMonContext User Path" /></p>


1. Create a NeoLoad Population SuperMonContext having only the userPath SuperMonContext
<p align="center"><img src="/screeshots/population.png" alt="Supermon Population" /></p>
1. Create a NeoLoad Scenario Using your population and the SuperMonContext Population
The SuperMonContext Population would need to be added to your NeoLoad scenario with the following settings :
* Duration : iteration
* Load Policy : Constant : 1 user doing 1 iteration
<p align="center"><img src="/screeshots/runtime.png" alt="Supermon scenario" /></p>

### Parameters for XrayContext
   
| Name             | Description |
| -----            | ----- |
| `schemeID`      | Scheme id of the database monitored by Supermon |
| `databaseType`  (Optional) |   Type of database ( mysql,oracle...Etc)|
| `databaseName` (Optional)  |  Name of the database |
| `useCaseIdentifier`  |  Name of the test |



## WebHook Handler

### Configuration
The webhook handler is a web service package in a container : `hrexed/neoload_supermon`
The container will required different type of Environment variables 

To be able to import NeoLoad test results you will need to specify :
* `NL_WEB_HOST`: Hostname of the webui of NeoLoad WEB
* `NL_API_HOST` : Hostname of the rest-api of NeoLoad WEB
* `NL_API_TOKEN` : API token of NeoLoad WEB ( [how to generate an API token](https://www.neotys.com/documents/doc/nlweb/latest/en/html/#24270.htm))
* `PORT`  : Port that the service will listen to
* `logging-level` : Logging level of the service ( DEBUG, INFO, ERROR)
* `SupermonAPIHostname` : Hostname of the the api of Supermon
* `SupermonPort` : Port of the supermon environment
* `SupermonAPIPath` : Path to reach to the api of Supermon
* `SUPERMON_OAUTH_PATH` : Path to reach Oauth token ( default : `/oauth/token`)
* `client_id` 
* `client_secret`
* `user` 
* `password` 
* `ssl` : True or false


#### Run the webhookHandler

Requirements : Server having :
* docker installed
* acessible from NeoLoad WEB ( Saas our your managemend instance of NeoLoad WEB)

THe deployment will use  :
* `/deployment/docker-compose` to connect to Supermon

Make sure to update the docker-compose file by specifying the Environment variables.

the deployment will be done by running the following command :
```bash
docker-compose -f <docker file> up -d
```
#### Configure the WebHook in your NeoLoad Web Account to send a notification to your WebHook service

The webhookhandler service is listenning to 2 disctinct endpoints :
* `/Health` : Get request build to check if the webhookhandler is up
* `/webhookstart` : POST request to receive the webhook from NeoLoad WEB for the test started event
* `/webhookend` :   POST request to receive the webhook from NeoLoad WEB for the test ended event

The Webhookhandler is expecting the following Json Payload for:
```json
{
	"testid" : "TESTID"
}
```

To configure the webhook in NeoLoad WEB you will need to :
1. Connect to NeoLoad WEB
2. Click on 
3. Click On the TAB named WebHook
4. Create a new Webhook ( [How to create a webhook](https://www.neotys.com/documents/doc/nlweb/latest/en/html/#27141.htm))
5. URL of the webhook : http://<IP of you WEBHOOKHANDLER>:8080/webhookend
6. Events : Test ended
7. Payload :
```json
{
            "testid": "$(test_id)"
}
```
<p align="center"><img src="/screeshots/webhook.png" alt="XrayContext webhok" /></p>
8. Create a new Webhook ( [How to create a webhook](https://www.neotys.com/documents/doc/nlweb/latest/en/html/#27141.htm))
9. URL of the webhook : http://<IP of you WEBHOOKHANDLER>:8080/webhookstart
10. Events : Test started
11. Payload :
```json
{
            "testid": "$(test_id)"
}
```
<p align="center"><img src="/screenshots/webhook.png" alt="SuperMon webhok" /></p>

#### Database monitoring coming in NeoLaod WEB dashboard
Once the integration configured , you will automatically receive monitoring data related to your database in Neoload WEB:
<p align="center"><img src="/screeshots/monitoring.png" alt="Supermon Data" /></p>


At the end of the test you will be able to access to detailed report related to the activty generated on your database.
The link to the report will be accesissible from the overview tab of your test :
<p align="center"><img src="/screeshots/link.png" alt="Supermon LInk " /></p>
