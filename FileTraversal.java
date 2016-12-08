import java.io.File;
import java.util.LinkedList;

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
