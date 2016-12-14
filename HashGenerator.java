import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;

/**
 * @author weichih.c
 * 
 * Generate all file hashs and output a csv text.
 */
public class HashGenerator{
	
	private static void argumentErrorAlert(){
		System.out.println("argument error.\n");
		System.out.println("java HashGenerator -f filePath || -d directoryPath\n");
		System.out.println("[-f] will get a md5 of a specific file");
		System.out.println("[-d] will get md5 of all files in a specific directory\n");
		System.exit(0);
	}

	public static void main(String[] args){
		if( args.length != 2 ){
			argumentErrorAlert();
		}

		switch( args[0].charAt(0) ){
			case '-':

				switch(args[0].charAt(1)){
					case 'f':	// if arg == '-f'
						
					try {
						File file = new File( args[1] );
						if( file.isDirectory() ){
							System.out.println("Error: File " + args[1] + " is not a file.");
						
						}else{
							String md5 = MD5Converter.md5Hash( file );	// calculate the hash value.
							System.out.println("MD5 Hash of " + args[1] + " is :" + md5);
						}
						
					} catch (IOException e) {
						System.out.println("Error: File " + args[1] + " doesn't exist.");
					}
						break;

					case 'd':	// if arg == '-d'
						File folder = new File( args[1] );
						if( !folder.isDirectory() ){
							System.out.println("Error: File " + args[1] + " is not a directory.");
							break;
						}
						FileTraversal ftraverse = new FileTraversal();
						LinkedList<File> fileList = ftraverse.listFilesForFolder(folder);
						
						Writer writer = null;

						try {
						    writer = new BufferedWriter(
						    			new OutputStreamWriter(
						    				new FileOutputStream("HashResult.csv"), "utf-8"));
						
						    for(int i=0; i<fileList.size(); i++){
						    	File f = fileList.get(i);
						    
								try {
									String md5 = MD5Converter.md5Hash(f);
									writer.write(i + ", ");
									writer.write(f.getName() + ", ");
									writer.write(f.getPath() + ", ");
									writer.write(md5);
									writer.write("\n");
//									System.out.println("MD5 Hash of " + f.getName() + " is :" + md5);
								} catch (IOException e) {
									System.out.println("Error: File " + args[1] + " doesn't exist.");
								}
								
							}
						
						} catch (IOException ex) {
							System.out.println("Error: Output file occurs error.");
							
						} finally 
						{
							System.out.println("The result of hash is saved in HashResult.csv\n");
							try {
								writer.close();
							} catch (Exception ex) {/*ignore*/}
						}
						
						
						break;
					default:
						argumentErrorAlert();
				}

				break;

			default:
				argumentErrorAlert();
		}

		System.exit(0);
	}

}