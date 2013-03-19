package com.mtoolkit.dbf;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;

/**
 * DBFReader class can creates objects to represent DBF data.
 * 
 * This Class is used to read data from a DBF file. Meta data and
 * records can be queried against this document.
 * 
 * <p> DBFReader cannot write anythng to a DBF file. For creating DBF files 
 * use DBFWriter.
 * 
 * <p> Fetching rocord is possible only in the forward direction and 
 * cannot re-wound. In such situation, a suggested approach is to reconstruct the object.
 * 
 * <p> The nextRecord() method returns an array of Objects and the types of these
 * Object are as follows:
 * 
 * <table>
 * 	<tr><th>xBase Type</th><th>Java Type</th></tr>
 * 	<tr><td>C</td><td>String</td></tr>
 * 	<tr><td>N</td><td>Integer</td></tr>
 * 	<tr><td>F</td><td>Double</td></tr>
 * 	<tr><td>L</td><td>Boolean</td></tr>
 * 	<tr><td>D</td><td>java.util.Date</td></tr>
 * </table>
 */
public class DbfReader extends DbfBase {

    DataInputStream dataInputStream;
    DbfHeader header;

    /* Class specific variables */
    boolean isClosed = true;
    /* Skip marking deleted records */
    private boolean skipDelete = true;

    /**
     * Read the record of default, the default is to skip the record has
     * been marked for deletion.
     *
     * @param in
     * @throws com.DbfException.dbf.DBFException
     */
    public DbfReader(InputStream in) throws DbfException {
        this(in, true);
    }

    /**
     * You can specify whether the filter has been marked deleted record.
     * true for the filter, false non-filtered.
     * @param in
     * @param skipDelete
     * @throws com.DbfException.dbf.DBFException
     */
    public DbfReader(InputStream in, boolean skipDelete) throws DbfException {

        try {

            this.dataInputStream = new DataInputStream(in);
            this.skipDelete = skipDelete;
            this.isClosed = false;
            this.header = new DbfHeader();
            this.header.read(this.dataInputStream);

            /* it might be required to leap to the start of records at times */
            int t_dataStartIndex = this.header.headerLength - (32 + (32 * this.header.fieldArray.length)) - 1;
            if (t_dataStartIndex > 0) {

                dataInputStream.skip(t_dataStartIndex);
            }
        } catch (IOException e) {

            throw new DbfException(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer(this.header.year + "/" + this.header.month + "/" + this.header.day + "\n" + "Total records: " + this.header.numberOfRecords +
                "\nHEader length: " + this.header.headerLength +
                "");

        for (int i = 0; i < this.header.fieldArray.length; i++) {

            sb.append(this.header.fieldArray[i].getName());
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns the number of records in the DBF.
     * @return 
     */
    public int getRecordCount() {

        return this.header.numberOfRecords;
    }

    /**
     * Returns the asked Field. In case of an invalid index,
     * it returns a ArrayIndexOutofboundsException.
     *
     * @param index Index of the field. Index of the first field is zero.
     * @return
     * @throws com.DbfException.dbf.DBFException
     */
    public DbfField getField(int index) throws DbfException {
        if (isClosed) {
            throw new DbfException("Source is not open");
        }

        return this.header.fieldArray[index];
    }

    /**
     * Returns the number of field in the DBF.
     * @return 
     * @throws com.DbfException.dbf.DBFException
     */
    public int getFieldCount() throws DbfException {

        if (isClosed) {

            throw new DbfException("Source is not open");
        }

        if (this.header.fieldArray != null) {

            return this.header.fieldArray.length;
        }

        return -1;
    }

    /**
     * Reads the returns the next row in the DBF stream.
     * @return The next row as an Object array. Types of the elements
     *  these arrays follow the convention mentioned in the class description.
     * @throws com.DbfException.dbf.DBFException
     */
    public Object[] nextRecord() throws DbfException {
        if (isClosed) {

            throw new DbfException("Source is not open");
        }

        Object recordObjects[] = new Object[this.header.fieldArray.length];

        try {

            boolean isDeleted = false;

            do {
                if (isDeleted) {
                    dataInputStream.skip(this.header.recordLength - 1);
                }
                int t_byte = dataInputStream.readByte();
                if (t_byte == END_OF_DATA) {

                    return null;
                }
                isDeleted = (t_byte == '*');

            } while (isDeleted && skipDelete);


            for (int i = 0; i < this.header.fieldArray.length; i++) {

                switch (this.header.fieldArray[i].getDataType()) {

                    case 'C':

                        byte b_array[] = new byte[this.header.fieldArray[i].getFieldLength()];
                        dataInputStream.read(b_array);
                        recordObjects[i] = new String(b_array, characterSetName);
                        break;

                    case 'D':

                        byte t_byte_year[] = new byte[4];
                        dataInputStream.read(t_byte_year);

                        byte t_byte_month[] = new byte[2];
                        dataInputStream.read(t_byte_month);

                        byte t_byte_day[] = new byte[2];
                        dataInputStream.read(t_byte_day);

                        try {

                            GregorianCalendar calendar = new GregorianCalendar(
                                    Integer.parseInt(new String(t_byte_year)),
                                    Integer.parseInt(new String(t_byte_month)) - 1,
                                    Integer.parseInt(new String(t_byte_day)));

                            recordObjects[i] = calendar.getTime();
                        } catch (NumberFormatException e) {
                            /* this field may be empty or may have improper value set */
                            recordObjects[i] = null;
                        }

                        break;

                    case 'F':

                        try {

                            byte t_float[] = new byte[this.header.fieldArray[i].getFieldLength()];
                            dataInputStream.read(t_float);
                            t_float = DbfUtils.trimLeftSpaces(t_float);
                            if (t_float.length > 0 && !DbfUtils.contains(t_float,
                                    (byte) '?')) {

                                recordObjects[i] = new Float(new String(t_float));
                            } else {

                                recordObjects[i] = null;
                            }
                        } catch (NumberFormatException e) {
                            recordObjects[i] = null;
//                            throw new DBFException("Failed to parse Float: " + e.
//                                    getMessage());
                        }

                        break;

                    case 'N':

                        try {

                            byte t_numeric[] = new byte[this.header.fieldArray[i].getFieldLength()];
                            dataInputStream.read(t_numeric);
                            t_numeric = DbfUtils.trimLeftSpaces(t_numeric);

                            if (t_numeric.length > 0 && !DbfUtils.contains(
                                    t_numeric, (byte) '?')) {

                                recordObjects[i] =
                                        new Double(new String(t_numeric));
                            } else {

                                recordObjects[i] = null;
                            }
                        } catch (NumberFormatException e) {

                            recordObjects[i] = null;
//                            throw new DBFException(
//                                    "Failed to parse Number: " + e.getMessage());
                        }

                        break;

                    case 'L':

                        byte t_logical = dataInputStream.readByte();
                        if (t_logical == 'Y' || t_logical == 't' || t_logical == 'T' || t_logical == 't') {

                            recordObjects[i] = Boolean.TRUE;
                        } else {

                            recordObjects[i] = Boolean.FALSE;
                        }
                        break;

                    case 'M':
                        // TODO Later
                        recordObjects[i] = new String("null");
                        break;

                    default:
                        recordObjects[i] = new String("null");
                }
            }
        } catch (EOFException e) {

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new DbfException(e.getMessage());
        }

        return recordObjects;
    }
}
