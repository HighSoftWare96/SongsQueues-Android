package com.highsoftware96.songsqueues.fragments.songlineups;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.activities.LineupPresentationActivity;
import com.highsoftware96.songsqueues.activities.MainActivity;
import com.highsoftware96.songsqueues.adapter.SongLineupsListAdapter;
import com.highsoftware96.songsqueues.dialogs.ConfirmDialog;
import com.highsoftware96.songsqueues.exampledata.ExampleDataManager;
import com.highsoftware96.songsqueues.models.local.Lineup;

import java.util.ArrayList;

public class SongLineupsFragment extends Fragment {

    protected ListView lineupsListView = null;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RelativeLayout opaquePanelForListViewMenu;
    private ArrayList<Lineup> testLineups;
    private SongLineupsListAdapter songLineupsListAdapter;

    private PopupWindow popupMenu;
    private int selectedItemPosition;
    private long selectedItemID;

    public SongLineupsFragment() {
        testLineups = onSetupLineupsListContent(null);
    }

    public ArrayList<Lineup> onSetupLineupsListContent(@Nullable String category) {
        ArrayList<Lineup> result = new ArrayList<>();
        // Required empty public constructor
        for (int i = 0; i < 3; i++) {
            result.add(ExampleDataManager.getOneLineUp());
        }
        return result;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_song_lineups, container, false);

        this.opaquePanelForListViewMenu = v.findViewById(R.id.opaquePanelForListViewMenu);
        // LISTVIEW setup
        this.lineupsListView = v.findViewById(R.id.lineups_listview);
        this.songLineupsListAdapter = new SongLineupsListAdapter(testLineups, this);
        lineupsListView.setAdapter(this.songLineupsListAdapter);
        // impostazione listener per il click su un elemento della lista: apro l'activity
        // con i dettagli della lineup
        lineupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // imposto la lineup selezionata (per sicurezza)
                setSelectedLineup(position, id);
                //view.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bounce));
                // TODO: l'utente ha clickato su una lineup: apro i dettagli e l'activity con tutti questi elementi
                Intent intentToSeeLineupDetails = new Intent();
                intentToSeeLineupDetails.setClass(getContext(), LineupPresentationActivity.class);
                // TODO: recuperare il lineup dalla fonte originale dei dati.... BOH....
                intentToSeeLineupDetails.putExtra(MainActivity.LINEUP_REFERRED_INTENT_EXTRA, testLineups.get(position));
                intentToSeeLineupDetails.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivityForResult(intentToSeeLineupDetails, MainActivity.LINEUP_DETAILS_SEEN_INTENT_HAS_TO_REFRESH);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.bounce);
            }
        });

        // Se l'utente preme per molto tempo su un elemento apro il menu
        // listener per click a lungo termine
        lineupsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // mostro il menu con tutte le opzioni
                showListItemMenu(position, id);
                // L'evento è consumato non procedo oltre!
                return true;
            }
        });

        //SWIPEREFRESH setup
        this.swipeRefreshLayout = v.findViewById(R.id.refresh_lineups_swipe);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshLayout();
                // TODO: faccio il refresh dei dati della app
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

    private void setSelectedLineup(int position, long id) {
        selectedItemID = id;
        selectedItemPosition = position;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // l'utente ha usato l'activity ed ha modificato l'intent: devo aggiornare la listview!
        if (requestCode == MainActivity.LINEUP_DETAILS_SEEN_INTENT_HAS_TO_REFRESH && resultCode == MainActivity.LINEUP_DETAILS_MODIFIED_REFRESH_DATA_REQUIRED) {
            // TODO: refreshare la home!
            onRefreshLayout();
            Toast.makeText(getContext(), "view da aggiornare!", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showListItemMenu(final int position, final long lineupID) {
        // imposto il lineup selezionato
        setSelectedLineup(position, lineupID);

        // Creo un menu di popup che compare dal basso
        popupMenu = new PopupWindow(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        // inserisco il layout con i tasti opzioni per l'elemento
        View itemMenuView = inflater.inflate(R.layout.lineup_item_menu_layout, null);
        // IMPOSTAZIONE DEI LISTENER DEi BOTTONi del menu
        Button favouriteButton = itemMenuView.findViewById(R.id.itemmenulineup_favourite_action);
        if (testLineups.get(position).getFavourite()) {
            favouriteButton.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.favourite_full_icon), null, null, null);
            favouriteButton.setText(getString(R.string.remove_favourite_itemmenu));
        }
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAVOURITE: " + position + "#" + lineupID, Toast.LENGTH_SHORT).show();
                if (!testLineups.get(position).getFavourite()) {
                    ((Button) v).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.favourite_full_icon), null, null, null);
                    ((Button) v).setText(getString(R.string.remove_favourite_itemmenu));
                    testLineups.get(position).setFavourite(true);
                    // TODO: renderlo favorito o no su cloud
                } else {
                    ((Button) v).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.favourite_empty_icon), null, null, null);
                    ((Button) v).setText(getString(R.string.select_as_favourite_itemmenu));
                    testLineups.get(position).setFavourite(false);
                }
            }
        });

        itemMenuView.findViewById(R.id.itemmenulineup_delete_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // recupero il tag con l'indice
                // SONO Nello scope quindi posso usare POSITION per trovare chi è stato clickato!
                new SongLineupsFragmentConfirmDialog(getString(R.string.sure_delete_lineup), getActivity(), null).show();
                // TODO: l'utente ha clickato sul bottone di delete dell'elemento
            }
        });
        itemMenuView.findViewById(R.id.itemmenulineup_share_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // recupero il tag con l'indice
                // SONO Nello scope quindi posso usare POSITION per trovare chi è stato clickato!
                Toast.makeText(getActivity(), "SHARE: " + position + "#" + lineupID, Toast.LENGTH_SHORT).show();
                // TODO: l'utente ha clickato sul bottone di share dell'elemento
            }
        });

        popupMenu.setFocusable(true);
        // altre impostazioni di layout
        popupMenu.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupMenu.setElevation(100);
        }
        // imposto i vincoli di grandezza del menu
        popupMenu.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupMenu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupMenu.setContentView(itemMenuView);
        // come animazione utilizzo quella definita qui per cui c'è uno slideup/down della finestrina
        popupMenu.setAnimationStyle(R.style.WindowMenu_animation);
        // opacizzo il pannello inferiore per mettere in risalto il menu
        opaquePanelForListViewMenu.animate().setDuration(200).alpha(0.8f);
        // mostro il menu
        popupMenu.showAsDropDown(getActivity().findViewById(R.id.popupMenuAnchorDivider));
        // imposto il listener per rendere trasparente il pannello inferiore
        popupMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                onRefreshLayout();
                // Tolgo il pannello dell'opacità sotto il menu
                opaquePanelForListViewMenu.animate().setDuration(200).alpha(0f);
            }
        });
    }

    public void onRefreshLayout() {
        this.songLineupsListAdapter.notifyDataSetChanged();
    }

    private void deleteItem() {
        Toast.makeText(getActivity(), "DELETE: " + this.selectedItemPosition + "#" + this.selectedItemID, Toast.LENGTH_SHORT).show();
        // TODO: eliminare l'elemento
    }

    /**
     * Classe specializzata per il confirm dialog nostro della cancellazione di un lineup
     *
     * @see ConfirmDialog
     */
    private class SongLineupsFragmentConfirmDialog extends ConfirmDialog {


        public SongLineupsFragmentConfirmDialog(@Nullable String query, Activity parentActivity, @Nullable View opaquePanelBackground) {
            super(query, parentActivity, opaquePanelBackground);
        }

        @Override
        public void onAfterConfirmDialogDismiss() {
            if (getConfirm()) {
                // l'utente ha messo SI
                deleteItem();
                // TODO: eliminato s
                popupMenu.dismiss();
            } else {
                // l'utente ha messo NO
                // TODO
            }
        }

    }
}
