package DesafioTeste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testes {

	static WebDriver driver;
	static WebDriverWait wait;

	public static void esperar(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void esperarObjetoVisivel(WebElement element, long timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void esperarObjetoClicavel(WebElement element, long timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void fecharBrowser() {
		driver.quit();
	}

	public static void maximizarBrowser() {
		driver.manage().window().maximize();
	}

	public static void navegar(String url) {
		driver.get(url);
	}

	public Testes() {
		PageFactory.initElements(driver, this);
	}

	@Before
	public void runBefore() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		loadPageObjects();
		maximizarBrowser();
		navegar("https://automacaocombatista.herokuapp.com/treinamento/home");

	}

	@After
	public void runAfter() {
		fecharBrowser();
	}

	public void loadPageObjects() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Formulário")
	WebElement formularioMenu;

	@FindBy(linkText = "Criar Usuários")
	WebElement createUserMenu;

	@FindBy(id = "user_name")
	WebElement userName;

	@FindBy(id = "user_lastname")
	WebElement userLastName;

	@FindBy(id = "user_email")
	WebElement userEmail;

	@FindBy(id = "user_address")
	WebElement userAddress;

	@FindBy(id = "user_university")
	WebElement userUniversity;

	@FindBy(id = "user_profile")
	WebElement userProfile;

	@FindBy(id = "user_gender")
	WebElement userGender;

	@FindBy(id = "user_age")
	WebElement userAge;

	@FindBy(name = "commit")
	WebElement createButton;

	@FindBy(xpath = "//p[@id='notice']")
	WebElement confirmationLabel;

	@FindBy(id = "error_explanation")
	WebElement failureMessageLabel;

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getUserLastName() {
		return userLastName;
	}

	public WebElement getUserEmail() {
		return userEmail;
	}

	public WebElement getUserAddress() {
		return userAddress;
	}

	public WebElement getUserUniversity() {
		return userUniversity;
	}

	public WebElement getUserProfile() {
		return userProfile;
	}

	public WebElement getUserGender() {
		return userGender;
	}

	public WebElement getUserAge() {
		return userAge;
	}

	public WebElement getFormularioLink() {
		return formularioMenu;
	}

	public WebElement getCreateUserMenu() {
		return createUserMenu;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public WebElement getConfirmationLabel() {
		return confirmationLabel;
	}

	public WebElement getFailureMessageLabel() {
		return failureMessageLabel;
	}

	@Test
	public void criarUsuarioComSucesso() throws Exception {
		esperarObjetoClicavel(getFormularioLink(), 5000);

		getFormularioLink().click();

		getCreateUserMenu().click();
		
		esperarObjetoClicavel(getCreateButton(), 5000);

		getUserName().sendKeys("Simone");

		getUserLastName().sendKeys("Não Lembro");

		getUserEmail().sendKeys("s.cteste@teste.com");

		getUserAddress().sendKeys("rua do teste");

		getUserUniversity().sendKeys("Teste de software");

		getUserProfile().sendKeys("Tester");

		getUserGender().sendKeys("Feminino");

		getUserAge().sendKeys("30");

		getCreateButton().click();

		boolean expectedConfirmationMessage = getConfirmationLabel().getText()
				.contains("Usuário Criado com sucesso");
		assertTrue(expectedConfirmationMessage);
	}

	@Test
	public void criarUsuarioSemIdade() throws Exception {

		esperarObjetoClicavel(getFormularioLink(), 5000);

		getFormularioLink().click();

		getCreateUserMenu().click();
		
		esperarObjetoClicavel(getFormularioLink(), 5000);

		getUserName().sendKeys("Simone");

		getUserLastName().sendKeys("Não Lembro");

		getUserEmail().sendKeys("s.cteste@teste.com");

		getUserAddress().sendKeys("rua do teste");

		getUserUniversity().sendKeys("Teste de software");

		getUserProfile().sendKeys("Tester");

		getUserGender().sendKeys("Feminino");

		getCreateButton().click();

		boolean expectedConfirmationMessage = getConfirmationLabel().getText()
				.contains("Usuário Criado com sucesso");
		assertTrue(expectedConfirmationMessage);
	}

	@Test
	public void criarUsuarioEmBranco() throws Exception {

		esperarObjetoClicavel(getFormularioLink(), 5000);

		getFormularioLink().click();

		getCreateUserMenu().click();
		
		esperarObjetoClicavel(getCreateButton(), 5000);
		
		getCreateButton().click();

		boolean expectedFailureMessageLabel = getFailureMessageLabel().getText()
				.contains("3 errors proibiu que este usuário fosse salvo");
		assertTrue(expectedFailureMessageLabel);
	}

}
