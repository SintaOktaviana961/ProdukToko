/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Produk;

public class ProdukController implements Initializable {

    @FXML private TextField txtKode;
    @FXML private TextField txtNama;
    @FXML private TextField txtHarga;
    @FXML private TextField txtCari;
    @FXML private ComboBox<String> cmbKategori;
    @FXML private TableView<Produk> tblProduk;
    @FXML private TableColumn<Produk, String> colKode;
    @FXML private TableColumn<Produk, String> colNama;
    @FXML private TableColumn<Produk, String> colKategori;
    @FXML private TableColumn<Produk, String> colHarga;

    private final ObservableList<Produk> daftarProduk =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbKategori.getItems().addAll("Makanan", "Minuman");

        colKode.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKode()));
        colNama.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNama()));
        colKategori.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKategori()));
        colHarga.setCellValueFactory(d -> new SimpleStringProperty(
                String.format("Rp %,.0f", d.getValue().getHarga())));

        tblProduk.setItems(daftarProduk);
        tblProduk.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // Dipanggil tombol "Tambah Produk"
    @FXML
    private void tambahProduk() {
        String kode = txtKode.getText().trim();
        String kategori = cmbKategori.getValue();

        if (kode.isEmpty() || kategori == null) {
            tampilPeringatan("Kode dan kategori wajib diisi!");
            return;
        }
        for (Produk p : daftarProduk) {
            if (p.getKode().equalsIgnoreCase(kode)) {
                tampilPeringatan("Kode produk sudah dipakai!");
                return;
            }
        }

        double harga;
        try {
            harga = Double.parseDouble(txtHarga.getText().trim());
        } catch (NumberFormatException e) {
            tampilPeringatan("Harga harus berupa angka!");
            return;
        }

        try {
            daftarProduk.add(new Produk(kode, txtNama.getText(), kategori, harga));
        } catch (IllegalArgumentException e) {
            tampilPeringatan(e.getMessage());
            return;
        }

        txtKode.clear();
        txtNama.clear();
        txtHarga.clear();
        cmbKategori.setValue(null);
        txtCari.clear();
        tblProduk.setItems(daftarProduk);
    }

    // Dipanggil tombol "Cari"
    @FXML
    private void cariProduk() {
        String kata = txtCari.getText().trim().toLowerCase();
        if (kata.isEmpty()) {
            tblProduk.setItems(daftarProduk);
            return;
        }
        ObservableList<Produk> hasil = FXCollections.observableArrayList();
        for (Produk p : daftarProduk) {
            if (p.getNama().toLowerCase().contains(kata)) {
                hasil.add(p);
            }
        }
        tblProduk.setItems(hasil);
    }

    // Dipanggil tombol "Lihat Detail"
    @FXML
    private void lihatDetail() {
        Produk dipilih = tblProduk.getSelectionModel().getSelectedItem();
        if (dipilih == null) {
            tampilPeringatan("Pilih produk di tabel terlebih dahulu!");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Detail.fxml"));
            Parent root = loader.load();

            DetailController dc = loader.getController();
            dc.setProduk(dipilih);   // kirim objek Produk ke halaman detail

            Stage stage = new Stage();
            stage.setTitle("Detail Produk");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            tampilPeringatan("Gagal membuka halaman detail: " + e.getMessage());
        }
    }

    private void tampilPeringatan(String pesan) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setContentText(pesan);
        alert.show();
    }
}