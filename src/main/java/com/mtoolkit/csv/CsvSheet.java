package com.mtoolkit.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * CSV sheet.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 07/17/2012
 * @since 	JDK1.5
 */
public class CsvSheet {
	
	/** CSV sheet rows */
	private CsvRow[] _rows;
	
	// ---- constructors ---------------------------------------------
	/**
	 * Creates CsvSheet instance with specified CSV file class path.
	 * 
	 * @param  classPath CSV file class path.
	 * 
	 * @throws IOException if an I/O exception occurs.
	 * @throws NullPointerException If {@code classPath} is null.
	 */
	public CsvSheet(String classPath) throws IOException {
		this(CsvSheet.class.getResourceAsStream(classPath));
	}
	
	/**
	 * Creates CsvSheet instance with specified CSV file.
	 * 
	 * @param  file CSV file.
	 * 
	 * @throws IOException 
	 * 		   if the file does not exist,
     *		   or is a directory rather than a regular file,
     *		   or for some other reason cannot be opened for reading.
     * @throws SecurityException 
     * 		   if a security manager exists and its {@code checkRead} 
     *		   method denies read access to the file.
	 */
	public CsvSheet(File file) throws IOException {
		this(new FileInputStream(file));
	}
	
	/**
	 * Creates CsvSheet instance with specified CSV URL.
	 * 
	 * @param  url CSV URL.
	 * 
	 * @throws IOException if an I/O exception occurs.
	 * @throws NullPointerException if {@code url} is null.
	 */
	public CsvSheet(URL url) throws IOException {
        this(url.openStream());
    }
	
	/**
	 * Creates CsvSheet instance with specified CSV input stream.
	 * 
	 * @param  input CSV input stream.
	 * 
	 * @throws IOException if an I/O error occurs whiling reading.
	 * @throws NullPointerException if {@code input} is null.
	 */
	public CsvSheet(InputStream input) throws IOException {
		_rows = readSheet(input);
	}
	
	/**
	 * Creates CsvSheet instance with specified CsvRow array.
	 * 
	 * @param rows row array.
	 */
	public CsvSheet(CsvRow[] rows) {
		_rows = (rows == null ? new CsvRow[0] : rows);
	}
	
	// ---- public methods -------------------------------------------
	/**
	 * Returns CsvRow array of this sheet file.
	 * 
	 * @return CsvRow array of this sheet file.
	 */
	public CsvRow[] getCsvRows() {
		return _rows;
	}
	
	/**
	 * Returns CsvRow number of this sheet file.
	 * 
	 * @return CsvRow number of this sheet file.
	 */
	public int getRowNumber() {
		return _rows.length;
	}
	
	/**
	 * Returns the specified CsvRow of this sheet file.
	 * 
	 * @param  index row index, start from 0.
	 * 
	 * @return the specified CsvRow of this sheet file.
	 */
	public CsvRow getCsvRow(int index) {
		return _rows[index];
	}
	
	/**
	 * Writes current CSV sheet to specified file.
	 * 
	 * @param  file output file.
	 * 
	 * @throws IOException 
	 * 		   if the file does not exist but cannot be created, 
	 * 		   or exists but is a directory rather than a regular file,
	 * 		   or cannot be opened for any other reason,
	 * 		   or an I/O error occurs whiling writing.
	 * @throws SecurityException  
	 * 		   if a security manager exists and its {@code checkWrite} 
	 * 		   method denies write access to the file.
	 */
	public void writeSheet(File file) throws IOException {
		wirteSheet(new FileOutputStream(file));
	}
	
	/**
	 * Writes current CSV sheet to specified output stream.
	 * 
	 * @param  output output stream.
	 * 
	 * @throws IOException if an I/O error occurs whiling writing.
	 */
	public void wirteSheet(OutputStream output) throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(output));
		try {
			for (int i = 0; i < _rows.length; i++) {
				writer.write(_rows[i].getRowValue());
				if (i != _rows.length - 1) {
					writer.newLine();
				}
			}
		} finally {
			writer.close();
		}
	}
	
	// ---- private methods ------------------------------------------
	/**
	 * Reads CsvRows from the specified input stream.
	 * 
	 * @param  input CSV sheet file input stream.
	 * 
	 * @return CsvRows array.
	 * 
	 * @throws IOException if an I/O error occurs whiling reading.
	 * @throws NullPointerException if {@code input} is null.
	 */
	private CsvRow[] readSheet(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(input));
		
		String row = null;
		List<CsvRow> rowList = new LinkedList<CsvRow>();
		try {
			while ((row = reader.readLine()) != null) {
				rowList.add(new CsvRow(row));
			}
		} finally {
			reader.close();
		}
		
		return rowList.toArray(new CsvRow[rowList.size()]);
	}
	
}
