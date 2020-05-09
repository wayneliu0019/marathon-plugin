# marathon-plugin

## Marathon Plugin Dependency

The Marathon plugin interface is needed to compile this package.

## Package

To build the package run this command:
`sbt clean pack`
This will compile and package all plugins.
The resulting jars with all dependencies are put into the directory: `target/pack/lib`.
This directory can be used directly as plugin directory for Marathon.

# Using a Plugin
1. Run `sbt clean pack` in the repository's root directory.
2. Locate the Plugin configuration file (look at the Plugin's README.md for a hint)).
3. Start Marathon with the following flags: `--plugin_dir target/pack/lib --plugin_conf <path_to_the_plugin_config_file>`

