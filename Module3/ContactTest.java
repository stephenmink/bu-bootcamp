import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest { 
  @Test 
  void constructor_setsNameCorrectly() { 
    Contact c = new Contact("Ada Lovelace", "+1 617 555 0101"); 
    assertEquals("Ada Lovelace", c.getName()); 
  } 
 
  @Test
  void constructor_setsPhoneCorrectly() { 
    Contact c = new Contact("Ada Lovelace", "+1 617 555 0101"); 
    assertEquals("+1 617 555 0101", c.getPhone()); 
  } 
 
  @Test
  void getName_returnsExactString_notTransformed() { 
    Contact c = new Contact("Grace Hopper", "555-0000"); 
    assertEquals("Grace Hopper", c.getName());
  } 
 
  @Test
  void toString_containsName() { 
    Contact c = new Contact("Alan Turing", "555-0001"); 
    assertTrue(c.toString().contains("Alan Turing"));
  } 
 
  @Test
  void toString_containsPhone() {
    Contact c = new Contact("Alan Turing", "555-0001");
    assertTrue(c.toString().contains("555-0001"));
  }

  @Test
  void twoContacts_withSameName_remainIndependent() {
    Contact a = new Contact("Jane Doe", "555-1111");
    Contact b = new Contact("Jane Doe", "555-2222");

    // Same name, but each keeps its own phone — they are separate objects.
    assertEquals("Jane Doe", a.getName());
    assertEquals("Jane Doe", b.getName());
    assertEquals("555-1111", a.getPhone());
    assertEquals("555-2222", b.getPhone());
    assertNotSame(a, b);
  }

  @Test
  void toString_formatsNameAndPhone_separatedByPipe() {
    Contact c = new Contact("Katherine Johnson", "555-0199");
    assertEquals("Katherine Johnson | 555-0199", c.toString());
  }
}
