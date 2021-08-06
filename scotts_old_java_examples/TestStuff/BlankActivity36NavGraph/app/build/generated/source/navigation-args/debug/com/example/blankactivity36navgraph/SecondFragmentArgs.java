package com.example.blankactivity36navgraph;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SecondFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private SecondFragmentArgs() {
  }

  private SecondFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static SecondFragmentArgs fromBundle(@NonNull Bundle bundle) {
    SecondFragmentArgs __result = new SecondFragmentArgs();
    bundle.setClassLoader(SecondFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("message")) {
      String message;
      message = bundle.getString("message");
      if (message == null) {
        throw new IllegalArgumentException("Argument \"message\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("message", message);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getMessage() {
    return (String) arguments.get("message");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("message")) {
      String message = (String) arguments.get("message");
      __result.putString("message", message);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    SecondFragmentArgs that = (SecondFragmentArgs) object;
    if (arguments.containsKey("message") != that.arguments.containsKey("message")) {
      return false;
    }
    if (getMessage() != null ? !getMessage().equals(that.getMessage()) : that.getMessage() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SecondFragmentArgs{"
        + "message=" + getMessage()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(SecondFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public SecondFragmentArgs build() {
      SecondFragmentArgs result = new SecondFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setMessage(@NonNull String message) {
      if (message == null) {
        throw new IllegalArgumentException("Argument \"message\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("message", message);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getMessage() {
      return (String) arguments.get("message");
    }
  }
}
