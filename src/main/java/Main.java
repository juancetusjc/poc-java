import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

public class Main {

	public static void main(String[] args) {

		try {

			String key = "{\"id\":\"cced6740aa5ac244f70c8e700eed87cf3b8670dcdeacdb500dafbfdc6f8167ef\",\"type\":\"cnpj\",\"count\":61858,\"file_id\":\"971224\",\"before_vsync\":\"c5af7875dd78b1590df2f3abbda2d8e2062d85b7a02027475fececcf2da3a13b\",\"current_vsync\":\"cced6740aa5ac244f70c8e700eed87cf3b8670dcdeacdb500dafbfdc6f8167ef\",\"status\":\"check_sync\",\"date_last_check\":\"2021-11-06T06:40:29.79-03:00\",\"bacen_id\":\"7204804\",\"bacen_vsync\":\"cced6740aa5ac244f70c8e700eed87cf3b8670dcdeacdb500dafbfdc6f8167ef\",\"bacen_last_modified\":\"2021-11-06T09:40:29.690Z\",\"entity_type\":\"vsync\",\"file_name\":\"10573521_CNPJ_971224_2021-11-06T09:40:45.796Z.txt\",\"date_created\":\"2021-11-03T23:08:39.933-03:00\",\"date_last_modified\":\"2021-11-06T21:24:14.265-03:00\",\"version\":62163,\"live_mode\":true}";
			String descomprimir="H4sIAAAAAAAAAFVQSW7rMAy9i9d1QWqkfI7uDQ0kIPw0zreVoEHRu1dqkQBdUXx6A8nPqZZpmcRTccKWdeCYQjI6GFI5MLINxMWaqBN3lCShdyCovVhLyRtdXKLpZTq26555eNUTr8f9nNe8vV/izv1z5/9XPtr6E1YoRZOzzBYsz0ZymMmqMqNm49ApioRd84/vv3ww0OMIrIUOt/tlpOSL9CaxbDuvtxHXQRWZRQJkdiESheSQAqBjo8Bn1NqigMVonIZAiYm9Vrp04145jDWiNN6fhqU4HcD36cSDRiWWMvYGbLZshB2mnJINhXMiKezFmOKoPzQIqTHv0WK7Ht0rlsKlA/xRxyXO6zjUtEg8HfygPYOf5HOr7b4+lq4DK7HxmnfuZZxHgcIZcQb3ptSi1WLpFZWaQS8AD/op9sz3rVSpf0X+DdyiwmLwVeNTc+P9qNt5WvBlOtUbD2kfoO1X/voG4rIhoDMCAAA=";
			String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";


			String jsonComprimir= Base64.getEncoder().encodeToString(compress(key));

			System.out.println ("string length:" + key.length());
			System.out.println ("after compression:: "+ jsonComprimir);
			System.out.println ("after decompression:" + uncompress(compress(jsonComprimir)));
			System.out.println ("after decompressing string::" + uncompressToString(compress(jsonComprimir)));

			// sacar base 64
			byte[] bDescompres= Base64.getDecoder().decode(jsonComprimir);
			System.out.println ("after decompressing string::" + uncompressToString(bDescompres));

			// sacar base json
			byte[] bDescompres2= Base64.getDecoder().decode(descomprimir);
			System.out.println ("after decompressing string::" + uncompressToString(bDescompres2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
	public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";


	public static byte[] compress(String str, String encoding) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encoding));
			gzip.close();
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static byte[] compress(String str) throws IOException {
		return compress(str, GZIP_ENCODE_UTF_8);
	}

	public static byte[] uncompress(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			GZIPInputStream ungzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static String uncompressToString(byte[] bytes, String encoding) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			GZIPInputStream ungzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString(encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String uncompressToString(byte[] bytes) {
		return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
	}

}
