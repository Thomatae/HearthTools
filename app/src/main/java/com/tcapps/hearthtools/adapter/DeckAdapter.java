package com.tcapps.hearthtools.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tcapps.hearthtools.R;

public class DeckAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewType type = ViewType.fromValue(viewType);

        View row = LayoutInflater.from(viewGroup.getContext()).inflate(type.accept(new LayoutVisitor(), null), viewGroup, false);

        return type.accept(new ViewHolderVisitor(), row);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 30 + 1 + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewType.HEADER.value;
        } else if (position == getItemCount() - 1) {
            return ViewType.FOOTER.value;
        } else {
            return ViewType.LIST_ITEM.value;
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public HeaderHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "I've Been Clicked", Toast.LENGTH_LONG).show();
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public FooterHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "I've Been Clicked", Toast.LENGTH_LONG).show();
        }
    }

    public static class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ListItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "I've Been Clicked", Toast.LENGTH_LONG).show();
        }
    }

    public static enum ViewType {
        HEADER (0) {
            @Override
            <T,V> T accept(Visitor<T,V> visitor, V value) {
                return visitor.visitHeader(value);
            }
        },
        LIST_ITEM (1) {
            @Override
            <T,V> T accept(Visitor<T,V> visitor, V value) {
                return visitor.visitListItem(value);
            }
        },
        FOOTER (2) {
            @Override
            <T,V> T accept(Visitor<T,V> visitor, V value) {
                return visitor.visitFooter(value);
            }
        };

        public int value;

        ViewType(int value) {
            this.value = value;
        }

        static ViewType fromValue(int value) {
            for (ViewType type : values()) {
                if (type.value == value) {
                    return type;
                }
            }

            throw new IllegalArgumentException("ViewType does not have a return type for that value. Something is incredibly messed up.");
        }

        abstract <T,V> T accept(Visitor<T,V> visitor, V value);

        public static interface Visitor<T,V> {
            T visitHeader(V value);
            T visitListItem(V value);
            T visitFooter(V value);
        }
    }

    public static class ViewHolderVisitor implements ViewType.Visitor<RecyclerView.ViewHolder, View> {

        @Override
        public RecyclerView.ViewHolder visitHeader(View value) {
            return new HeaderHolder(value);
        }

        @Override
        public RecyclerView.ViewHolder visitListItem(View value) {
            return new ListItemHolder(value);
        }

        @Override
        public RecyclerView.ViewHolder visitFooter(View value) {
            return new FooterHolder(value);
        }
    }

    public static class LayoutVisitor implements ViewType.Visitor<Integer, Void> {

        @Override
        public Integer visitHeader(Void value) {
            return R.layout.row_deck_list;
        }

        @Override
        public Integer visitListItem(Void value) {
            return R.layout.row_deck_details;
        }

        @Override
        public Integer visitFooter(Void value) {
            return R.layout.deck_details_footer;
        }
    }

}
