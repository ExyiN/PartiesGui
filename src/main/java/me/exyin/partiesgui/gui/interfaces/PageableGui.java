package me.exyin.partiesgui.gui.interfaces;

import com.alessiodp.parties.api.interfaces.PartyPlayer;

import java.util.List;

public interface PageableGui {
  void setupContent();
  void setupFooter();
  List<List<PartyPlayer>> getPages();
  int getPIndex();
  void setPIndex(int pIndex);
}
