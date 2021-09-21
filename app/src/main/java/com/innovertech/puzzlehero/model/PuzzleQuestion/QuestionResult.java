package com.innovertech.puzzlehero.model.PuzzleQuestion;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult implements Parcelable {
    @SerializedName("msisdn_found")
    @Expose
    public String msisdnFound;
    @SerializedName("isSubscribed")
    @Expose
    public Boolean isSubscribed;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("is_correct")
    @Expose
    public Boolean isCorrect;
    @SerializedName("correct_answer")
    @Expose
    public String correctAnswer;
    @SerializedName("total_point")
    @Expose
    public Integer totalPoint;

    protected QuestionResult(Parcel in) {
        msisdnFound = in.readString();
        byte tmpIsSubscribed = in.readByte();
        isSubscribed = tmpIsSubscribed == 0 ? null : tmpIsSubscribed == 1;
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        byte tmpIsCorrect = in.readByte();
        isCorrect = tmpIsCorrect == 0 ? null : tmpIsCorrect == 1;
        correctAnswer = in.readString();
        if (in.readByte() == 0) {
            totalPoint = null;
        } else {
            totalPoint = in.readInt();
        }
    }

    public static final Creator<QuestionResult> CREATOR = new Creator<QuestionResult>() {
        @Override
        public QuestionResult createFromParcel(Parcel in) {
            return new QuestionResult(in);
        }

        @Override
        public QuestionResult[] newArray(int size) {
            return new QuestionResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msisdnFound);
        dest.writeByte((byte) (isSubscribed == null ? 0 : isSubscribed ? 1 : 2));
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        dest.writeByte((byte) (isCorrect == null ? 0 : isCorrect ? 1 : 2));
        dest.writeString(correctAnswer);
        if (totalPoint == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalPoint);
        }
    }
}
