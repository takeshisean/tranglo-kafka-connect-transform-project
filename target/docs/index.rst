=======================================
tranglo-kafka-connect-transform-project
=======================================

This plugin is used to add additional JSON parsing functionality to Kafka Connect.



.. toctree::
    :hidden:
    :maxdepth: 1
    :glob:

    sources/*


.. toctree::
    :hidden:
    :maxdepth: 1
    :glob:

    sinks/*


.. toctree::
    :hidden:
    :maxdepth: 1
    :glob:

    transformations/*


.. toctree::
    :hidden:
    :maxdepth: 1
    :glob:

    converters/*


.. toctree::
    :hidden:
    :maxdepth: 1

    schemas


------------
Installation
------------

The preferred method of installation is to utilize the `Confluent Hub Client <https://docs.confluent.io/current/connect/managing/confluent-hub/client.html>`_.

^^^^^^^^^^^^^
Confluent Hub
^^^^^^^^^^^^^
The plugin is hosted on the `Confluent Hub <https://www.confluent.io/hub/com.tranglo.kafka.connect.transform/tranglo-kafka-connect-transform-project>`_. Installation through the
`Confluent Hub Client <https://docs.confluent.io/current/connect/managing/confluent-hub/client.html>`_ is simple. Use the following command line.


.. code-block:: bash

    confluent-hub install com.tranglo.kafka.connect.transform/tranglo-kafka-connect-transform-project:latest


^^^^^^^^^^^^^^^^^^^
Manual Installation
^^^^^^^^^^^^^^^^^^^

#. Compile the source code with `mvn clean package`
#. Create a subdirectory called `tranglo-kafka-connect-transform-project` under the `plugin.path` on your connect worker.
#. Extract the contents of the zip file from `target/components/packages/` to the directory you created in the previous step.
#. Restart the connect worker.
