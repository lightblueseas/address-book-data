package de.alpharogroup.address.book.db.init;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.alpharogroup.db.init.AbstractDatabaseInitialization;


public class DatabaseInitialization extends AbstractDatabaseInitialization
{

	public DatabaseInitialization(final Properties databaseProperties)
	{
		super(databaseProperties);
	}

	@Override
	protected List<File> getScriptFiles()
	{
		final File insertsDir = getInsertDir();
		final List<File> scriptFiles = new ArrayList<>();
		scriptFiles.add(new File(insertsDir, "insertCountries.sql"));
		scriptFiles.add(new File(insertsDir, "insertAllFederalStates.sql"));
		scriptFiles.add(new File(insertsDir, "insertAllKnownZipcodes.sql"));
		scriptFiles.add(new File(insertsDir, "insertAllCountriesZipcodesToAddresses.sql"));
		return scriptFiles;
	}

}
