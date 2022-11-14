package stepdefinitions;

import java.io.File;
import java.util.List;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.poc.base.BaseTest;
import com.poc.pages.HomePage;
import com.poc.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchAndNavigate {
	public String propFile = "resources" + File.separator + "data.properties";
	public String dataFile = "resources" + File.separator + "TestData.xlsx";
	public String[] sheet = { "testdata", "config" };
	public String[] config = null;
	public String[] sc_01 = null;
	BaseTest base = new BaseTest();
	Utils util = new Utils();
	HomePage homePage = null;
	List<Float> pricesLowToHigh = null;
	List<Float> pricesHighToLow = null;

	@BeforeMethod
	public void getData() {
		config = util.toReadExcelData(dataFile, sheet[1], "C1");
		sc_01 = util.toReadExcelData(dataFile, sheet[0], "Sc_01");

	}

	@Given("I am a non-registered customer")
	public void i_am_a_non_registered_customer() {

		base.driverInit(config[2]);
	}

	@Given("I navigate to www.ebay.co.uk")
	public void i_navigate_to_www_ebay_co_uk() {

		homePage = base.navigateTo(config[1]);
		homePage.waitForSearchFld();
		String actual = homePage.getPageTitle();
		String expected = config[3];
		System.out.println(actual + "---> " + expected);

		AssertJUnit.assertTrue(actual.equalsIgnoreCase(expected));
	}

	@When("I search for an item")
	public void i_search_for_an_item() {
		homePage.searchProduct(sc_01[1]);
	}

	@Then("I get a list of matching results")
	public void i_get_a_list_of_matching_results() {
		Integer expected = base.util.getNum(sc_01[2]);
		Integer actual = homePage.matchingResults();
		AssertJUnit.assertTrue(actual > expected);
	}

	@Then("the resulting items cards show: postage price, No of bids, price or show BuyItNow tag")
	public void the_resulting_items_cards_show_postage_price_no_of_bids_price_or_show_buy_it_now_tag() {
		homePage.getProductDetails();
	}

	@When("I sort the results by Lowest Price")
	public void i_sort_the_results_by_lowest_price() {
		pricesLowToHigh = homePage.sortByLowest();
	}

	@Then("the results are listed in the page in the lowest to Highest order")
	public void the_results_are_listed_in_the_page_in_the_lowest_to_Highest_order() {
		homePage.comparePrices(pricesLowToHigh);
	}

	@When("I sort the results by Highest Price")
	public void i_sort_the_results_by_highest_price() {
		pricesHighToLow = homePage.sortByHighest();
	}

	@Then("the results are listed in the page in the Highest to lowest order")
	public void the_results_are_listed_in_the_page_in_the_Highest_to_lowest_order() {
		homePage.comparePrices(pricesHighToLow);
	}

	@Then("the results show more than one page")
	public void the_results_show_more_than_one_page() {
		homePage.verifyPaginationDisplay();
	}

	@Then("the user can navigate through the pages to continue looking at the items")
	public void the_user_can_navigate_through_the_pages_to_continue_looking_at_the_items() {
		homePage.navigatePages(2);
		homePage.navigatePages(3);
		homePage.navigatePages(4);
	}

	@AfterMethod
	@After(order = 0)
	public void tearDown() {
		base.driver.quit();
	}
}
