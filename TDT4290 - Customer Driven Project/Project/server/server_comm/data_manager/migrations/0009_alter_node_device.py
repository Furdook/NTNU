# Generated by Django 5.1.1 on 2024-11-08 14:45

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('data_manager', '0008_alter_category_communication_protocols'),
    ]

    operations = [
        migrations.AlterField(
            model_name='node',
            name='device',
            field=models.ForeignKey(
                blank=True,
                null=True,
                on_delete=django.db.models.deletion.CASCADE,
                to='data_manager.device',
            ),
        ),
    ]
