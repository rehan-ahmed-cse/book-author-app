package com.example.bookauthor.dto;

/**
 * DTO (Data Transfer Object) for the custom INNER JOIN query.
 * This class holds combined data from both Book and Author tables.
 * Using a DTO avoids circular reference issues and improves performance.
 */
public class BookAuthorDTO {

    private Long bookId;
    private String bookTitle;
    private String genre;
    private Integer publicationYear;
    private String authorName;
    private String nationality;

    /**
     * Constructor matching the JPQL SELECT new statement.
     * The order of parameters MUST match the query.
     */
    public BookAuthorDTO(Long bookId, String bookTitle, String genre, 
                         Integer publicationYear, String authorName, String nationality) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.authorName = authorName;
        this.nationality = nationality;
    }

    // ===== GETTERS ONLY (immutable DTO) =====
    public Long getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public String getGenre() { return genre; }
    public Integer getPublicationYear() { return publicationYear; }
    public String getAuthorName() { return authorName; }
    public String getNationality() { return nationality; }
}
