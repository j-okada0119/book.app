package com.book.common;

import lombok.Getter;

@Getter
public enum BookGenre {

	JAVA(0, "Java"),
	PHP(1, "PHP"),
	C(2, "C"),
	GO(3, "GO");

	private int genreId;

	private String genreNm;

	private BookGenre(int genreId, String genreNm) {
		this.genreId = genreId;
		this.genreNm = genreNm;
	}

	public static BookGenre findByGenreId(int genreId) {
		for(BookGenre bG : BookGenre.values()) {
			if (bG.getGenreId() == genreId) {
				return bG;
			}
		}
		return null;
	}
}
