import java.io.File;
import java.io.IOException;

import java.util.LinkedList;


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
						String md5 = MD5Converter.md5Hash( args[1] );	// calculate the hash value.
						
						System.out.println("MD5 Hash of " + args[1] + "is :\n");
						System.out.println( md5 );
						
						
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
						for(File f : fileList){
							System.out.println(f.getName());
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