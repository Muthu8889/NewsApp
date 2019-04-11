import { AppPage } from './app.po';
import {browser, by, element} from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    browser.driver.manage().window().maximize();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('NewsAppBoilerplateJava');
  });
  it('should login to application', () =>{
    browser.sleep(1000);
    browser.element(by.id('userId')).sendKeys('arun');
    browser.element(by.id('password')).sendKeys('arun');
    browser.element(by.id('login')).click();
    expect(browser.getCurrentUrl()).toContain('/home');
    browser.sleep(1000);
  });
  it('should able to go to news details', () => {
    browser.sleep(1000);
    browser.element(by.id('more')).click();
    browser.sleep(1000);
  });
  it('should add news to watchlist', () => {
    browser.sleep(1000);
    browser.element(by.id('addBtn')).click();
    browser.sleep(1000);
    browser.sleep(1000);
  });
  it('should able to news in watchlist',() => {
    browser.sleep(1000);
    browser.element(by.id('watchlist')).click();
    expect(browser.getCurrentUrl()).toContain('/watchlist');
    browser.sleep(1000);
  });
  it('should able to edit news',() => {
    browser.sleep(1000);
    browser.element(by.id('edit')).click();
    expect(browser.getCurrentUrl()).toContain('/edit');
    browser.sleep(1000);
  });
  it("should able to logout",() => {
    browser.sleep(1000);
    browser.element(by.id('log-out')).click();
    browser.sleep(1000);
    browser.sleep(1000);
  })
});
