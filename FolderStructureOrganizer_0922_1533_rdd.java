// 代码生成时间: 2025-09-22 15:33:32
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * FolderStructureOrganizer is a utility class to reorganize the folder structure.
 * It ensures files are placed in the appropriate directories based on file types.
 */
public class FolderStructureOrganizer {

    private String sourceFolderPath;
    private List<FileType> fileTypes;

    /*
     * Constructor to initialize the source folder path and the list of file types.
     */
    public FolderStructureOrganizer(String sourceFolderPath, List<FileType> fileTypes) {
        this.sourceFolderPath = sourceFolderPath;
        this.fileTypes = fileTypes;
    }

    /*
     * Organizes the folder structure by moving files to the appropriate directories.
     * @throws IOException if an I/O error occurs.
     */
    public void organizeFolders() throws IOException {
        File sourceFolder = new File(sourceFolderPath);
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            throw new IOException("The source folder does not exist or is not a directory.");
        }

        // Iterate over each file type and move files accordingly.
        for (FileType fileType : fileTypes) {
            File targetFolder = new File(sourceFolderPath + File.separator + fileType.getDirectory());
            if (!targetFolder.exists() && !targetFolder.mkdirs()) {
                throw new IOException("Failed to create target directory: " + fileType.getDirectory());
            }

            File[] files = sourceFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(fileType.getExtension())) {
                        Path sourcePath = Paths.get(file.getAbsolutePath());
                        Path targetPath = Paths.get(targetFolder.getAbsolutePath(), file.getName());
                        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        }
    }

    /*
     * Represents a file type with its extension and the corresponding directory.
     */
    public static class FileType {
        private String extension;
        private String directory;

        public FileType(String extension, String directory) {
            this.extension = extension;
            this.directory = directory;
        }

        public String getExtension() {
            return extension;
        }

        public String getDirectory() {
            return directory;
        }
    }

    /*
     * Main method to test the FolderStructureOrganizer.
     */
    public static void main(String[] args) {
        try {
            List<FolderStructureOrganizer.FileType> fileTypes = new ArrayList<>();
            fileTypes.add(new FolderStructureOrganizer.FileType("txt", "TextFiles"));
            fileTypes.add(new FolderStructureOrganizer.FileType("jpg", "Images"));
            fileTypes.add(new FolderStructureOrganizer.FileType("mp3", "Audio"));

            FolderStructureOrganizer organizer = new FolderStructureOrganizer("/path/to/source/folder", fileTypes);
            organizer.organizeFolders();
            System.out.println("Folder structure organized successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}