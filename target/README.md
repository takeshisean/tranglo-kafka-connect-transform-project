# Introduction
[Documentation](https://jcustenborder.github.io/kafka-connect-documentation/projects/tranglo-kafka-connect-transform-project) | [Confluent Hub](https://www.confluent.io/hub/com.tranglo.kafka.connect.transform/tranglo-kafka-connect-transform-project)

This plugin is used to add additional JSON parsing functionality to Kafka Connect.

# Installation

## Confluent Hub

The following command can be used to install the plugin directly from the Confluent Hub using the
[Confluent Hub Client](https://docs.confluent.io/current/connect/managing/confluent-hub/client.html).

```bash
confluent-hub install com.tranglo.kafka.connect.transform/tranglo-kafka-connect-transform-project:latest
```

## Manually

The zip file that is deployed to the [Confluent Hub](https://www.confluent.io/hub/com.tranglo.kafka.connect.transform/tranglo-kafka-connect-transform-project) is available under
`target/components/packages/`. You can manually extract this zip file which includes all dependencies. All the dependencies
that are required to deploy the plugin are under `target/kafka-connect-target` as well. Make sure that you include all the dependencies that are required
to run the plugin.

1. Create a directory under the `plugin.path` on your Connect worker.
2. Copy all of the dependencies under the newly created subdirectory.
3. Restart the Connect worker.

# Converters
## [Super Converter](https://jcustenborder.github.io/kafka-connect-documentation/projects/tranglo-kafka-connect-transform-project/sources/MyConverter.html)

```
com.tranglo.kafka.connect.transform.MyConverter
```
This is a description of this connector and will show up in the documentation

# Source Connectors
## [Super Source Connector](https://jcustenborder.github.io/kafka-connect-documentation/projects/tranglo-kafka-connect-transform-project/sources/MySourceConnector.html)

```
com.tranglo.kafka.connect.transform.MySourceConnector
```

This is a description of this connector and will show up in the documentation
### Important

This is a important information that will show up in the documentation.
### Note

This is a note that will show up in the documentation
### Tip

This is a tip that will show up in the documentation.
### Configuration

#### General


##### `my.setting`

This is a setting important to my connector.

*Importance:* HIGH

*Type:* STRING




# Sink Connectors
## [Super Sink Connector](https://jcustenborder.github.io/kafka-connect-documentation/projects/tranglo-kafka-connect-transform-project/sinks/MySinkConnector.html)

```
com.tranglo.kafka.connect.transform.MySinkConnector
```

This is a description of this connector and will show up in the documentation
### Important

This is a important information that will show up in the documentation.
### Note

This is a note that will show up in the documentation
### Tip

This is a tip that will show up in the documentation.
### Configuration

#### General


##### `my.setting`

This is a setting important to my connector.

*Importance:* HIGH

*Type:* STRING




# Transformations
## [EpochtoTimestamp](https://jcustenborder.github.io/kafka-connect-documentation/projects/tranglo-kafka-connect-transform-project/transformations/EpochtoTimestamp.html)

*Key*
```
com.tranglo.kafka.connect.transform.EpochtoTimestamp$Key
```
*Value*
```
com.tranglo.kafka.connect.transform.EpochtoTimestamp$Value
```


### Configuration

#### General


##### `field`

The field containing the timestamp

*Importance:* HIGH

*Type:* STRING




## [Super Cool Transformation](https://jcustenborder.github.io/kafka-connect-documentation/projects/tranglo-kafka-connect-transform-project/transformations/MyKeyValueTransformation.html)

*Key*
```
com.tranglo.kafka.connect.transform.MyKeyValueTransformation$Key
```
*Value*
```
com.tranglo.kafka.connect.transform.MyKeyValueTransformation$Value
```

This transformation will change one record to another record.
### Configuration

#### General


##### `my.setting`

This is a setting important to my connector.

*Importance:* HIGH

*Type:* STRING





# Development

## Building the source

```bash
mvn clean package
```

## Contributions

Contributions are always welcomed! Before you start any development please create an issue and
start a discussion. Create a pull request against your newly created issue and we're happy to see
if we can merge your pull request. First and foremost any time you're adding code to the code base
you need to include test coverage. Make sure that you run `mvn clean package` before submitting your
pull to ensure that all of the tests, checkstyle rules, and the package can be successfully built.