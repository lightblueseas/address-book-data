package de.alpharogroup.address.book.init;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.file.csv.CsvFileUtils;
import de.alpharogroup.file.read.ReadFileUtils;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.file.write.WriteFileUtils;
import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.xml.XmlExtensions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitializeZipcodes {


	private static CountriesService countriesService;

	private static AddressesService addressesService;
	
	private static FederalstatesService federalstatesService;
	
	public static final String FILE_ENCODING = "UTF-8";
	public static final String DB_USER = "postgres";
	public static final String DB_NAME = "usermanagement";
	public static final String DB_HOST = "localhost";
	public static final String INSERT_ZIPCODES_PREFIX = "INSERT INTO zipcodes ( id, city, zipcode, country_id) VALUES \n"; 

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(final String[] args) throws ClassNotFoundException,
			SQLException, IOException {
//		initializeSpringBeans();
		readGermanZipcodes();
	}
	
	public static void readGermanZipcodes() throws IOException {
		File smr = PathFinder.getSrcMainResourcesDir();
		File deDir = PathFinder.getRelativePath(smr, "zipcodes",
				"de");
		
		File deZipcodesCsvFile = new File(deDir, "DE.txt");

		List<DeZipcodeBean> deZipcodeBeanList = new ArrayList<DeZipcodeBean>();
		List<String> lines = CsvFileUtils.readFileToList(deZipcodesCsvFile);
		for (String line : lines) {
			String[] entries = line.split("	");
			int last = entries.length-1;
			int beforeLast = last-1;
			DeZipcodeBean bean = new DeZipcodeBean();
			bean.setIso3166A2name(entries[0]);
			bean.setZipcode(entries[1]);
			bean.setCity(entries[2]);
			bean.setLatitude(entries[beforeLast]);
			bean.setLongitude(entries[last]);
			System.out.println(bean);
			deZipcodeBeanList.add(bean);
		}
		File output = new File(deDir, "DeZipcodes.xml");
		String xml = XmlExtensions.toXmlWithXStream(deZipcodeBeanList);
		WriteFileUtils.string2File(output, xml);
	}
	
	
	
	public static void readUnitedKingdomCountries() throws IOException{
//		File smr = PathFinder.getSrcMainResourcesDir();
//		File ukDir = PathFinder.getRelativePathTo(smr, "zipcodes",
//				"uk");
//		File ukZipcodesCsvFile = new File(ukDir, "postcodes.csv");
//		@SuppressWarnings("serial")
//		Set<String> countries = new HashSet<String>(){{
//			add("England");
//			add("Northern Ireland");
//			add("Scotland");
//			add("Wales");
//		}};
//		List<UkZipcodeBean> ukZipcodeBeanList = new ArrayList<UkZipcodeBean>();
//		List<String> lines = CsvFileUtils.readFileToList(ukZipcodesCsvFile);
//		lines.remove(0);
//		for (String line : lines) {
//			String[] entries = line.split(",");
//			UkZipcodeBean bean = new UkZipcodeBean();
//			bean.setZipcode(entries[0]);
//			bean.setLatitude(entries[1]);
//			bean.setLongitude(entries[2]);
//			bean.setCity(entries[7]);
//			if(countries.contains(entries[11].trim())){
//				bean.setCountry(entries[11].trim());
//			} else if(countries.contains(entries[12].trim())) {
//				bean.setCountry(entries[12].trim());
//			} else if(countries.contains(entries[13].trim())) {
//				bean.setCountry(entries[13].trim());
//			} else {
//				System.out.println("10:"+entries[10] + ":: 11:"+entries[11]+ ":: 12:"+entries[12]+ ":: 13:"+entries[13]);				
//			}
//			ukZipcodeBeanList.add(bean);
//		}
//		System.out.println(ukZipcodeBeanList.size());
//		for (String country : countries) {
//			System.out.println(country);
//		}

	}


	protected static void initializeSpringBeans() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
		        new String[] {"test-applicationContext.xml"});

		addressesService = applicationContext.getBean(AddressesService.class);
		countriesService = applicationContext.getBean(CountriesService.class);
		federalstatesService = applicationContext.getBean(FederalstatesService.class);
		
	}
	
	protected static void verifyZipcodes() throws IOException {
		// alien id, native id
		Map<String, String> federalStateMap = new HashMap<String, String>();
		federalStateMap.put("08", "2631");// Baden-Württemberg
		federalStateMap.put("09", "2632");// Bayern
		federalStateMap.put("11", "2633");// Berlin
		federalStateMap.put("12", "2634");// Brandenburg
		federalStateMap.put("04", "2635");// Bremen
		federalStateMap.put("02", "2636");// Hamburg
		federalStateMap.put("06", "2637");// Hessen
		federalStateMap.put("13", "2638");// Mecklenburg-Vorpommern
		federalStateMap.put("03", "2639");// Niedersachsen
		federalStateMap.put("05", "2640");// Nordrhein-Westfalen
		federalStateMap.put("07", "2641");// Rheinland-Pfalz
		federalStateMap.put("10", "2642");// Saarland
		federalStateMap.put("14", "2643");// Sachsen
		federalStateMap.put("15", "2644");// Sachsen-Anhalt
		federalStateMap.put("01", "2645");// Schleswig-Holstein
		federalStateMap.put("16", "2646");// Thüringen
		
		List<GermanZipcodeBean> list = getGermanZipcodeBeanList();
		Countries germany = countriesService.find("DE");
		
		for (GermanZipcodeBean bean : list) {
			String zipcode = bean.getZipcode();
			String federalStateKey = bean.getFederalStateKey();
			Addresses address = addressesService.findFirst(germany, zipcode);
			if(address != null) {
				Federalstates federalstate = federalstatesService.get(Integer.valueOf(federalStateMap.get(federalStateKey)));
				address.setFederalstate(federalstate);
				addressesService.merge(address);
			}			
		}		
	}
	

	public static Set<Zipcodes> findExistingZipcodesFromAddresses() {
		List<Addresses> addresses = addressesService.findAll();
		Set<Zipcodes> processed = new HashSet<Zipcodes>();
		for (Addresses address : addresses) {
			Zipcodes zc = address.getZipcode();
			if (zc != null) {
				processed.add(address.getZipcode());
			}
		}
		return processed;
	}


	protected static void getZipcodesFromProperties() throws IOException {
		Properties deZipCity = new Properties();
		InputStream is = ClassExtensions.getResourceAsStream("zipcodes/CH_zip_city.properties");
		deZipCity.load(is);
		Set<Entry<Object,Object>>  entries = deZipCity.entrySet();
		StringBuilder sb = new StringBuilder();
		sb.append(INSERT_ZIPCODES_PREFIX);
		int countryId = 213;
		int count = 44603;
		for(Entry<Object,Object> entry : entries) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			String[] splitted = value.split(";");
			for(String s : splitted){ 
				String repl = s.replaceAll("'", "''");
				sb.append("(" +
						count +
						", '"+ repl + "', '"+ key+"', " + countryId + "),\n");
				
				count++;
			}
		}
		System.out.println(sb.toString());
	}
	
	
	public static void printGermanZipcodeBeans() throws IOException {

		List<GermanZipcodeBean> list = getGermanZipcodeBeanList();
		int i = 1;
		for (GermanZipcodeBean bean : list) {
			String zipcode = bean.getZipcode();
			
			String city = bean.getCity();
			
			String circleKey = bean.getCircleKey();
			
			String circle = bean.getCircle();
			
			String federalStateKey = bean.getFederalStateKey();
			
			String federalState = bean.getFederalState();
			
			System.out.println(i+++".)"+zipcode+ "\t" + city+ "\t" + circleKey+ "\t" + circle+ "\t" + federalStateKey+ "\t" + federalState);
		}

	}


	private static List<GermanZipcodeBean> getGermanZipcodeBeanList()
			throws IOException {
		File smr = PathFinder.getSrcMainResourcesDir();
		File deDir = PathFinder.getRelativePath(smr, "zipcodes",
				"de");

		File germanZipcodesXmlFile = new File(deDir, "GermanZipcodes.xml");
		String notPrZipcodes = ReadFileUtils.readFromFile(germanZipcodesXmlFile);
		List<GermanZipcodeBean> list = XmlExtensions.toObjectWithXStream(notPrZipcodes);
		return list;
	}

}
