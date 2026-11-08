/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Thinkpad
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Produk;

public class DetailController {

    @FXML private Label lblKode;
    @FXML private Label lblNama;
    @FXML private Label lblKategori;
    @FXML private Label lblHarga;
    @FXML private Button btnTutup;

    public void setProduk(Produk p) {
        lblKode.setText("Kode: " + p.getKode());
        lblNama.setText("Nama: " + p.getNama());
        lblKategori.setText("Kategori: " + p.getKategori());
        lblHarga.setText(String.format("Harga: Rp %,.0f", p.getHarga()));
    }

    @FXML
    private void tutup() {
        ((Stage) btnTutup.getScene().getWindow()).close();
    }
}
