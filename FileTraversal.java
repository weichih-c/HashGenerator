import java.io.File;
import java.util.LinkedList;

/**
 * @author weichih.c
 * 
 * This class can recursive traversal a specific directory,
 * and return a list of files in the directory.
 *
 */
public class FileTraversal {
	LinkedList<File> fileList = new LinkedList<>();

	public LinkedList<File> listFilesForFolder(final File folder) {
				
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	listFilesForFolder(fileEntry);
	        } else {
	        	fileList.add(fileEntry);
	        }
	    }
	    
	    return fileList;
	}

}
