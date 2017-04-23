# Overview


Parent project that holds module projects for the persistence of address data.

The project holds backend data for simple address data management.

## License

The source code comes under the liberal MIT License, making address-book-data great for all types of applications that need address relevant data.

# Build status and latest maven version
[![Build Status](https://travis-ci.org/lightblueseas/address-book-data.svg?branch=master)](https://travis-ci.org/lightblueseas/address-book-data)

This erd-diagramm was created with the awesome [SQuirreL SQL Client](http://squirrel-sql.sourceforge.net/)

## Maven Central

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/address-book-data/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/address-book-data)

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~address-book-data~~~) for latest snapshots and releases.

Add the following maven dependencies to your project `pom.xml` if you want to import the core functionality:

You can first define the version properties:

	<properties>
			...
		<!-- address-book-data version -->
		<address-book-data.version>3.11.0</address-book-data.version>
		<address-book-business.version>${address-book-data.version}</address-book-business.version>
		<address-book-domain.version>${address-book-data.version}</address-book-domain.version>
		<address-book-entities.version>${address-book-data.version}</address-book-entities.version>
		<address-book-init.version>${address-book-data.version}</address-book-init.version>
		<address-book-rest-api.version>${address-book-data.version}</address-book-rest-api.version>
		<address-book-rest-client.version>${address-book-data.version}</address-book-rest-client.version>
		<address-book-rest-web.version>${address-book-data.version}</address-book-rest-web.version>
			...
	</properties>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-business:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-business</artifactId>
				<version>${address-book-business.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-domain:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-domain</artifactId>
				<version>${address-book-domain.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-entities:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-entities</artifactId>
				<version>${address-book-entities.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-init:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-init</artifactId>
				<version>${address-book-init.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-rest-api:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-rest-api</artifactId>
				<version>${address-book-rest-api.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-rest-client:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-rest-client</artifactId>
				<version>${address-book-rest-client.version}</version>
			</dependency>
			...
		</dependencies>

Add the following maven dependency to your project `pom.xml` if you want to import the functionality of address-book-rest-web:

		<dependencies>
			...
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>address-book-rest-web</artifactId>
				<version>${address-book-rest-web.version}</version>
			</dependency>
			...
		</dependencies>

## Want to Help and improve it? ###

The source code for address-book-data are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [lightblueseas/address-book-data/fork](https://github.com/lightblueseas/address-book-data/fork)

To share your changes, [submit a pull request](https://github.com/lightblueseas/address-book-data/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer

Do not hesitate to contact the address-book-data developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/lightblueseas/address-book-data/issues).

## Note

No animals were harmed in the making of this library.

# Donate

If you like this library, please consider a donation through 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Faddress-book-data" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>
