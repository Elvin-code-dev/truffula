import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TruffulaOptionsTest {

  @Test
  void testValidDirectoryIsSet(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = { "-nc", "-h", directoryPath };

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that the root directory is set correctly
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testDefaultFlagsOnlyPath(@TempDir File tempDir) throws Exception {
    File directory = new File(tempDir, "elvinsFolder");
    directory.mkdir();

    String[] args = { directory.getAbsolutePath() };

    TruffulaOptions options = new TruffulaOptions(args);

    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertFalse(options.isShowHidden());
    assertTrue(options.isUseColor());
  }

  @Test
  void testShowHiddenOnly(@TempDir File tempDir) throws Exception {
    File directory = new File(tempDir, "elvinsFolder");
    directory.mkdir();

    String[] args = { "-h", directory.getAbsolutePath() };

    TruffulaOptions options = new TruffulaOptions(args);

    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertTrue(options.isUseColor());
  }

  @Test
  void testNoColorOnly(@TempDir File tempDir) throws Exception {
    File directory = new File(tempDir, "elvinsFolder");
    directory.mkdir();

    String[] args = { "-nc", directory.getAbsolutePath() };

    TruffulaOptions options = new TruffulaOptions(args);

    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertFalse(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testZeroArgumentsThrowsException() {
    String[] args = {};

    assertThrows(IllegalArgumentException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testMoreThanThreeArgumentsThrowsException(@TempDir File tempDir) {
    File directory = new File(tempDir, "elvinsFolder");
    directory.mkdir();

    String[] args = { "-nc", "-h", "extra", directory.getAbsolutePath() };

    assertThrows(IllegalArgumentException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testUnknownFlagThrowsException(@TempDir File tempDir) {
    File directory = new File(tempDir, "elvinsFolder");
    directory.mkdir();

    String[] args = { "-x", directory.getAbsolutePath() };

    assertThrows(IllegalArgumentException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testInvalidDirectoryThrowsException() {
    String[] args = { "-h", "not_a_real_folder_12345" };

    assertThrows(FileNotFoundException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testFileInsteadOfDirectoryThrowsException(@TempDir File tempDir) throws Exception {
    File file = new File(tempDir, "file.txt");
    file.createNewFile();

    String[] args = { file.getAbsolutePath() };

    assertThrows(FileNotFoundException.class, () -> {
      new TruffulaOptions(args);
    });
  }
}
