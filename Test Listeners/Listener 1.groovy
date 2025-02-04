import org.apache.commons.lang3.SystemUtils

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords

class TestListener {
	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	
	@BeforeTestCase
	def sampleBeforeTestCase(TestSuiteContext testSuiteContext) {
		addDefaultPackages()
	}
	private static void listOfPackage(String directoryName, Set<String> pack) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				String path = file.getPath();
				String prefixPath = SystemUtils.IS_OS_WINDOWS ? "Include\\scripts\\groovy" : "Include/scripts/groovy";
				String packName = path.substring(path.lastIndexOf(prefixPath), path.lastIndexOf(File.separator));
				pack.add(packName.replace(File.separator, '.'));
			} else if (file.isDirectory()) {
				listOfPackage(file.getAbsolutePath(), pack);
			}
		}
	}

	private static List<String> getPackagesFromIncludeScriptFolder() {
		Set<String> files = new TreeSet<>();
		listOfPackage("Include/scripts/groovy", files);
		Iterator<String> i = files.iterator();
		List<String> packageNames = new ArrayList<>();
		while (i.hasNext()) {
			String name = i.next().replaceFirst("Include.scripts.groovy.", "");
			if (!name.equals("Include.scripts.groovy")) {
				// remove default package
				addPackage(name, packageNames);
			}
		}
		return packageNames;
	}

	public static void addDefaultPackages() {
		CucumberBuiltinKeywords.GLUE = getPackagesFromIncludeScriptFolder();
	}

	private static void addPackage(String name, List<String> packageNames) {
		if (!name.isEmpty()) {
			boolean match = anyMatch(packageNames, name);
			if (!match) {
				packageNames.add(name);
			}
		}
	}

	private static boolean anyMatch(List<String> packageNames, String name) {
		for (String element : packageNames) {
			if (name.indexOf(element + ".") == 0) {
				return true;
			}
		}
		return false;
	}
}