package kioja.com.musv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.List;

import kioja.com.musv.R;

/**
 * Created by Henrique on 07/07/2015.
 */
public class PostagemAdapter extends ArrayAdapter<ParseObject> {
    protected Context mContext;
    protected List<ParseObject> listPostagens;

    public PostagemAdapter(Context context, List listPostagens) {
        super(context, R.layout.rowlayout_lista_inicio, listPostagens);
        mContext = context;
        this.listPostagens = listPostagens;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;



        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.rowlayout_lista_inicio, null);
            holder = new ViewHolder();
            holder.imagem = null;


            holder.textNome = (TextView) convertView
                    .findViewById(R.id.textNome);
            holder.textCidade = (TextView) convertView
                    .findViewById(R.id.textCidade);
            holder.imagem = (ImageView) convertView
                    .findViewById(R.id.imagem);
            holder.textPerfil = (TextView) convertView
                    .findViewById(R.id.textPerfil);
            holder.textPostagem = (TextView) convertView
                    .findViewById(R.id.textPostagem);


            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }


        ParseObject postagemObject = listPostagens.get(position);
        /*
        ParseRelation relation = postagemObject.getRelation("Perfil");
        ParseObject perfilObject = null;

        try {
            List listPerfil = relation.getQuery().find();
            perfilObject = (ParseObject) listPerfil.get(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.textNome.setText(perfilObject.getString("Nome"));
        holder.textPerfil.setText(perfilObject.getBoolean("Artista") ? "Artista": "Produtor de Eventos");



        ParseFile parseFile = perfilObject.getParseFile("Imagem");
        if(parseFile != null){
            byte[] bytes = new byte[0];
            try {
                bytes = parseFile.getData();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(bytes != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.imagem.setImageBitmap(bmp);
            }


        }

        holder.textCidade.setText(perfilObject.getString("Cidade"));
        holder.textPostagem.setText(postagemObject.getString("Texto"));
   */

        return convertView;
    }

    public static class ViewHolder {
        TextView textNome;
        TextView textCidade;
        ImageView imagem;
        TextView textPerfil;
        TextView textPostagem;

    }

}