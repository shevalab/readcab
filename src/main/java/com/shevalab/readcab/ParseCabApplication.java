package com.shevalab.readcab;

import dorkbox.cabParser.CabException;
import dorkbox.cabParser.CabParser;
import dorkbox.cabParser.structure.CabFileEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class ParseCabApplication implements CommandLineRunner {

	@Autowired
	private CabParserDomHelper domHelper;

	@Autowired
	private CabParserSaxHelper saxHelper;

	public static void main(String[] args) {
		SpringApplication.run(ParseCabApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		domHelper.parseCabFile();
//		RevisionToFile index = saxHelper.parseIndexFile();
		saxHelper.parseCabFile();
		System.out.println("KB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024);

		System.exit(0);


		if(args.length < 1) {
			System.out.println("input CAB file path has to be supplied as parameter");
			System.exit(1);
		}
		Path offlineScanFile = Paths.get(args[0]);
		Path extractedDir = Paths.get(offlineScanFile.getParent().toString(), "extracted");
		if(!Files.exists(extractedDir)) {
			Files.createDirectory(extractedDir);
		}
		File extractedDirFile = extractedDir.toFile();

//		CabParser parser = new CabParser(new FileInputStream(args[0]), extractedDirFile );
//		ByteArrayOutputStream outputStream = parser.extractStream();
//
//		Files.list(extractedDir)
//				.map(p -> p.getFileName().toString())
//				.filter(packageName -> packageName.startsWith("package") && packageName.endsWith(".cab"))
//				.forEach(fName -> {
//					System.out.println("Extracting " + fName);
//					File extractDirForPackageCab = new File(extractedDirFile, fName.substring(0, fName.indexOf('.')));
//					extractDirForPackageCab.mkdir();
//					try {
//						new CabParser(new FileInputStream(new File(extractedDirFile, fName)), extractDirForPackageCab)
//										.extractStream();
//					} catch (CabException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//
//				});
//


		CabFileEntry[] files = extractCabFile(extractedDirFile, args[0]);

		System.out.println("Extracting package.xml");
		CabParser parser2 = new CabParser(new FileInputStream(new File(extractedDirFile, "package.cab")), "package.xml");
		ByteArrayOutputStream byteArrayOutputStream = parser2.extractStream();
		FileOutputStream packageCabOutputStream = new FileOutputStream(new File(extractedDirFile, "package.xml"));
		packageCabOutputStream.write(byteArrayOutputStream.toByteArray());
		System.out.println("Extracting packageN.cab files");
		Stream.of(files)
				.map(CabFileEntry::getName)
				.filter(packageName -> packageName.startsWith("package") && packageName.endsWith(".cab") && packageName.length() > 11)
				.forEach(fName -> {
					File extractDirForPackageCab = new File(extractedDirFile, fName.substring(0, fName.indexOf('.')));
					extractDirForPackageCab.mkdir();
					try {
						extractCabFile(extractDirForPackageCab, new File(extractedDirFile, fName).getAbsolutePath());

					} catch (CabException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				});
	}


	private CabFileEntry[] extractCabFile(File extractedDirFile, String inputFile) throws CabException, IOException {
		CabParser parser = new CabParser(new FileInputStream(inputFile), "*");
		CabFileEntry[] files = parser.files;
		int cnt = 0;
		for(CabFileEntry entry: files) {
			System.out.println("Extracting file: " + entry.getName());
			parser = new CabParser(new FileInputStream(inputFile), entry.getName());
			ByteArrayOutputStream byteArrayOutputStream = parser.extractStream();
			FileOutputStream packageCabOutputStream = new FileOutputStream(new File(extractedDirFile, entry.getName()));
			packageCabOutputStream.write(byteArrayOutputStream.toByteArray());
//			if(++cnt == 2) break; // extract only the package.cab file
		}
		return files;
	}
}
