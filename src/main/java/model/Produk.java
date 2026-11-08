/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thinkpad
 */
public class Produk {
    private final String kode;
    private final String nama;
    private final String kategori;
    private final double harga;

    public Produk(String kode, String nama, String kategori, double harga) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong!");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari 0!");
        }
        this.kode = kode;
        this.nama = nama.trim();
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public double getHarga() { return harga; }
}