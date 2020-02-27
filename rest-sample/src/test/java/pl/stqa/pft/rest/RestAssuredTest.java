package pl.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTest {

    @BeforeClass
    public void init() {
      RestAssured.authentication = RestAssured.basic("fe3613dd9667dff97f49df7c3c54b2a7", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Test issue#22").withDescription("New test issue#22");
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
    }

    private int createIssue(Issue newIssue) throws IOException {
      String json = RestAssured.given()
              .parameter("subject", newIssue.getSubject())
              .parameter("description", newIssue.getDescription())
              .post("http://demo.bugify.com/api/issues.json").asString();
      JsonElement parsed = new JsonParser().parse(json);
      parsed.getAsJsonObject().get("issue_id").getAsInt();
      return 0;
    }

    private Set<Issue> getIssues() throws IOException {
      String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor() {
      return Executor.newInstance()
              .auth("fe3613dd9667dff97f49df7c3c54b2a7", "");
    }
  }


