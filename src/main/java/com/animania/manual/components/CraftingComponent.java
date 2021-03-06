package com.animania.manual.components;

import java.util.Iterator;
import java.util.List;

import com.animania.Animania;
import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class CraftingComponent implements IManualComponent
{
	private List<IRecipe> recipes;
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;

	private int recipeIndex = 0;
	private ItemStack[][] items;
	private int[] ingredientIndex = new int[9];
	private IRecipe currentRecipe;

	private static final ResourceLocation MATRIX_TEXTURE = new ResourceLocation(Animania.MODID, "textures/gui/crafting_matrix.png");
	private static final ResourceLocation BUTTONS = new ResourceLocation(Animania.MODID, "textures/gui/recipe_buttons.png");

	public static int ITEM_TIMER = 0;

	public CraftingComponent(int x, int y, List<IRecipe> recipes)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.recipes = recipes;
		this.mc = Minecraft.getMinecraft();

		this.objectHeight = 54;
		this.objectWidth = 104;
	}

	@Override
	public void init()
	{
		currentRecipe = recipes.get(recipeIndex);
		items = getSortedIngredients(currentRecipe);
		ingredientIndex = new int[9];
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		mc.renderEngine.bindTexture(MATRIX_TEXTURE);
		int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;

		int posX = absoluteX + manual.guiLeft + border;
		int posY = absoluteY + manual.guiTop;

		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1);
		GlStateManager.enableBlend();
		manual.drawModalRectWithCustomSizedTexture(posX, posY, 0, 0, objectWidth, objectHeight, objectWidth, objectHeight);
		GlStateManager.popMatrix();

		for (int i = 0; i < 9; i++)
		{
			int offsetX = 1 + (18 * (i % 3));
			int offsetY = 1 + (18 * (i / 3));
			if (items[i].length > 0)
			{
				GlStateManager.pushMatrix();
				RenderHelper.enableGUIStandardItemLighting();
				manual.drawItemStack(items[i][ingredientIndex[i]], posX + offsetX, posY + offsetY, null);
				GlStateManager.popMatrix();
			}
		}

		GlStateManager.pushMatrix();
		RenderHelper.enableGUIStandardItemLighting();
		manual.drawItemStack(currentRecipe.getRecipeOutput(), posX + 83, posY + 19, null);
		GlStateManager.popMatrix();

		if (recipes.size() > 1)
		{
			mc.renderEngine.bindTexture(BUTTONS);
			manual.drawModalRectWithCustomSizedTexture(posX + 56, posY + 42, isHovering(mouseX, mouseY, posX + 56, posY + 42, 9, 11) ? 18 : 0, 0, 9, 11, 36, 11);
			manual.drawModalRectWithCustomSizedTexture(posX + 67, posY + 42, isHovering(mouseX, mouseY, posX + 67, posY + 42, 9, 11) ? 27 : 9, 0, 9, 11, 36, 11);
		}

		if (!(currentRecipe instanceof IShapedRecipe))
		{
			mc.fontRenderer.drawString(I18n.translateToLocal("manual.crafting.shapeless"), posX + 57, posY + 2, 0);
		}
		
		GlStateManager.disableLighting();
	}

	@Override
	public void update()
	{
		updateIngredientIndices();
	}

	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;
		int posX = absoluteX + manual.guiLeft + border;
		int posY = absoluteY + manual.guiTop;

		GlStateManager.pushMatrix();

		for (int i = 0; i < 9; i++)
		{
			int offsetX = 1 + (18 * (i % 3));
			int offsetY = 1 + (18 * (i / 3));
			if (isHovering(mouseX, mouseY, posX + offsetX, posY + offsetY, 16, 16))
			{
				if (items[i].length > 0)
				{
					manual.renderToolTip(items[i][ingredientIndex[i]], mouseX, mouseY);
				}
			}
		}

		if (isHovering(mouseX, mouseY, posX + 83, posY + 19, 16, 16))
			manual.renderToolTip(currentRecipe.getRecipeOutput(), mouseX, mouseY);

		GlStateManager.disableLighting();
		GlStateManager.popMatrix();

	}

	public boolean isHovering(int mouseX, int mouseY, int x, int y, int dx, int dy)
	{
		return mouseX > x && mouseX < x + dx && mouseY > y && mouseY < y + dy;
	}

	private void updateIngredientIndices()
	{
		if (!GuiScreen.isShiftKeyDown())
		{
			if (ITEM_TIMER == 0)
			{
				for (int i = 0; i < items.length; i++)
				{
					ItemStack[] ings = items[i];
					if (ings.length > 1)
					{
						int currentIndex = ingredientIndex[i];

						if (currentIndex == ings.length - 1)
							currentIndex = 0;
						else
							currentIndex++;

						ingredientIndex[i] = currentIndex;
					}
				}
			}
		}
	}

	@Override
	public void onLeftClick(int mouseX, int mouseY)
	{
		if (recipes.size() > 1)
		{
			int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;

			int posX = absoluteX + manual.guiLeft + border;
			int posY = absoluteY + manual.guiTop;

			if (isHovering(mouseX, mouseY, posX + 56, posY + 42, 9, 11))
			{
				if (recipeIndex == 0)
					recipeIndex = recipes.size() - 1;
				else
					recipeIndex--;

				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				init();
				return;
			}

			if (isHovering(mouseX, mouseY, posX + 67, posY + 42, 9, 11))
			{
				if (recipeIndex == recipes.size() - 1)
					recipeIndex = 0;
				else
					recipeIndex++;
				init();
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return;
			}
		}
	}

	@Override
	public void onRightClick(int mouseX, int mouseY)
	{
	}

	@Override
	public int getObjectWidth()
	{
		return objectWidth;
	}

	@Override
	public int getObjectHeight()
	{
		return objectHeight;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public IManualComponent setX(int x)
	{
		this.x = x;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		return this;
	}

	@Override
	public IManualComponent setY(int y)
	{
		this.y = y;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;
		return this;
	}

	@Override
	public void onLeftClick()
	{
	}

	@Override
	public void onRightClick()
	{
	}

	private ItemStack[][] getSortedIngredients(IRecipe recipe)
	{
		ItemStack[][] sortedIngredients = new ItemStack[9][0];

		int craftingWidth = 3;
		int craftingHeight = 3;
		int recipeWidth = recipe instanceof IShapedRecipe ? ((IShapedRecipe) recipe).getRecipeWidth() : craftingWidth;
		int l = 0;
		Iterator<Ingredient> iterator = recipe.getIngredients().iterator();

		loop: for (int i1 = 0; i1 < craftingHeight; ++i1)
		{
			for (int j1 = 0; j1 < recipeWidth; ++j1)
			{
				if (!iterator.hasNext())
				{
					break loop;
				}

				Ingredient ingredient = iterator.next();

				if (ingredient != Ingredient.EMPTY)
				{
					sortedIngredients[l] = ingredient.getMatchingStacks();
				}

				++l;
			}

			if (recipeWidth < craftingWidth)
			{
				l += craftingWidth - recipeWidth;
			}
		}

		return sortedIngredients;
	}
}
